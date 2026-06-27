import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class LaboratorioHuellas extends Agent {

    protected void setup() {
        registrarServicio("analisis-huellas");

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();

                if (msg != null) {
                    ACLMessage reply = msg.createReply();
                    reply.setContent("Huellas: coincidencia parcial con sospechoso B");
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
            System.out.println("[Huellas] Servicio registrado: " + tipo);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}