import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class PerfiladorCriminal extends Agent {

    protected void setup() {
        registrarServicio("perfil-criminal");

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();

                if (msg != null) {
                    String datos = msg.getContent();
                    String perfil;

                    if (datos.contains("coincidencia alta") && datos.contains("Huellas")) {
                        perfil = "Perfilador: sospechoso A con prioridad alta. Evidencia fuerte.";
                    } else if (datos.contains("coincidencia alta")) {
                        perfil = "Perfilador: sospechoso A con prioridad media-alta.";
                    } else if (datos.contains("Huellas")) {
                        perfil = "Perfilador: sospechoso B con prioridad media.";
                    } else {
                        perfil = "Perfilador: caso inconcluso.";
                    }

                    System.out.println("[Perfilador] " + perfil);
                    enviarAlFiscal(perfil);
                } else {
                    block();
                }
            }
        });
    }

    private void enviarAlFiscal(String perfil) {
        ServiceDescription sd = new ServiceDescription();
        sd.setType("informe-fiscal");

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(sd);

        try {
            DFAgentDescription[] resultado = DFService.search(this, dfd);

            if (resultado.length > 0) {
                ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
                msg.addReceiver(resultado[0].getName());
                msg.setContent(perfil);
                send(msg);
            }

        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }

    private void registrarServicio(String tipo) {
        ServiceDescription servicio = new ServiceDescription();
        servicio.setType(tipo);
        servicio.setName(getLocalName());

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(servicio);

        try {
            DFService.register(this, dfd);
            System.out.println("[Perfilador] Servicio registrado: " + tipo);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}