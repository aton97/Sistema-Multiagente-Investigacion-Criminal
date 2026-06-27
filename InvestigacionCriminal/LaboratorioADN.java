import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class LaboratorioADN extends Agent {

    protected void setup() {
        registrarServicio("analisis-adn");

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();

                if (msg != null) {
                    String evidencia = msg.getContent();
                    ACLMessage reply = msg.createReply();

                    if (evidencia.equalsIgnoreCase("sangre")) {
                        reply.setContent("ADN: coincidencia alta con sospechoso A");
                    } else if (evidencia.equalsIgnoreCase("cabello")) {
                        reply.setContent("ADN: coincidencia media con sospechoso A");
                    } else {
                        reply.setContent("ADN: evidencia no procesable");
                    }

                    send(reply);
                } else {
                    block();
                }
            }
        });
    }

    private void registrarServicio(String tipo) {
        ServiceDescription servicio = new ServiceDescription();
        servicio.setType(tipo);
        servicio.setName(getLocalName());

        DFAgentDescription dfd = new DFAgentDescription();
        dfd.addServices(servicio);

        try {
            DFService.register(this, dfd);
            System.out.println("[ADN] Servicio registrado: " + tipo);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}