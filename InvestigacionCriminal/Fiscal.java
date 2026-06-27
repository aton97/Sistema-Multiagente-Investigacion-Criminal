import jade.core.Agent;
import jade.core.behaviours.*;
import jade.domain.DFService;
import jade.domain.FIPAAgentManagement.*;
import jade.domain.FIPAException;
import jade.lang.acl.ACLMessage;

public class Fiscal extends Agent {

    protected void setup() {
        registrarServicio("informe-fiscal");

        addBehaviour(new CyclicBehaviour(this) {
            public void action() {
                ACLMessage msg = receive();

                if (msg != null) {
                    String perfil = msg.getContent();

                    System.out.println("[Fiscal] Recibí perfil criminal:");
                    System.out.println("[Fiscal] " + perfil);

                    if (perfil.contains("prioridad alta")) {
                        System.out.println("[Fiscal] Informe final: caso viable para investigación formal.");
                    } else if (perfil.contains("media")) {
                        System.out.println("[Fiscal] Informe final: se recomienda ampliar evidencias.");
                    } else {
                        System.out.println("[Fiscal] Informe final: caso no concluyente.");
                    }
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
            System.out.println("[Fiscal] Servicio registrado: " + tipo);
        } catch (FIPAException e) {
            e.printStackTrace();
        }
    }
}