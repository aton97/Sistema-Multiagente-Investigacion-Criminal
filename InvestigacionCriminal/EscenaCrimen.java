import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class EscenaCrimen extends Agent {

    private int respuestasLaboratorio = 0;
    private String resultados = "";

    protected void setup() {
        Object[] args = getArguments();

        if (args == null || args.length == 0) {
            System.out.println("[Escena] No se recibieron evidencias.");
            return;
        }

        System.out.println("[Escena] Iniciando investigación criminal...");

        for (Object arg : args) {
            String evidencia = (String) arg;
            System.out.println("[Escena] Evidencia encontrada: " + evidencia);

            if (evidencia.equalsIgnoreCase("sangre") || evidencia.equalsIgnoreCase("cabello")) {
                buscarYEnviar("analisis-adn", evidencia);
            }

            if (evidencia.equalsIgnoreCase("huella")) {
                buscarYEnviar("analisis-huellas", evidencia);
            }
        }

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();

                if (msg != null) {
                    System.out.println("[Escena] Respuesta recibida: " + msg.getContent());
                    resultados += msg.getContent() + " | ";
                    respuestasLaboratorio++;

                    if (respuestasLaboratorio >= args.length) {
                        enviarAlPerfilador(resultados);
                    }
                } else {
                    block();
                }
            }
        });
    }

    private void buscarYEnviar(final String tipoServicio, final String evidencia) {
        addBehaviour(new OneShotBehaviour(this) {
            public void action() {
                ServiceDescription sd = new ServiceDescription();
                sd.setType(tipoServicio);

                DFAgentDescription dfd = new DFAgentDescription();
                dfd.addServices(sd);

                try {
                    DFAgentDescription[] resultado = DFService.search(myAgent, dfd);

                    if (resultado.length > 0) {
                        ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                        msg.addReceiver(resultado[0].getName());
                        msg.setContent(evidencia);
                        myAgent.send(msg);

                        System.out.println("[Escena] Enviando " + evidencia + " a " + tipoServicio);
                    } else {
                        System.out.println("[Escena] No se encontró servicio: " + tipoServicio);
                    }

                } catch (FIPAException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void enviarAlPerfilador(String resultados) {
        ServiceDescription sd = new ServiceDescription();
        sd.setType("perfil-criminal");

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(sd);

        try {
            DFAgentDescription[] resultado = DFService.search(this, dfd);

            if (resultado.length > 0) {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(resultado[0].getName());
                msg.setContent(resultados);
                send(msg);

                System.out.println("[Escena] Resultados enviados al perfilador.");
            }

        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}