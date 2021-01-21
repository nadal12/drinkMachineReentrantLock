public class Replenisher implements Runnable {

    private final int id;

    //Declaració del monitor.
    private final DrinkMachineMonitor monitor;

    public Replenisher(int id, DrinkMachineMonitor monitor) {
        this.id = id;
        this.monitor = monitor;
    }

    @Override
    public void run() {
        System.out.println("Reposador " + id + " arriba");

        // Mentre hi hagi clients es reposarà la màquina.
        while (monitor.hasClients()) {
            int refilledQuantity = monitor.refillMachine(); //Reposar la màquina.
            if (refilledQuantity > 0) {
                System.out.println("Reposador " + id + " reposa la màquina, hi ha " + (monitor.getMachineCapacity() - refilledQuantity) + " refrescs i en posa " + refilledQuantity);
            }
        }

        //Reposador acaba.
        System.out.println("Reposador " + id + " se'n va");
    }
}
