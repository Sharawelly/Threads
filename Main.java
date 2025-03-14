//Learning threads and semaphores

//public class Main {
//    public static class MyRunnable implements Runnable {
//        @Override
//        public void run() {
//            for (int i = 0; i < 1000; i++) {
//                System.out.println("MyRunnable is running! " + i);
//                if (Thread.interrupted()) {
//                    System.out.println("Interrupted!");
//                    break;
//                }
//                Thread.yield(); // one one one one
//
//            }
//
//        }
//    }
//
//    public static class MyThread extends Thread {
//        String name;
//
//        MyThread(String name) {
//            this.name = name;
//        }
//
//        @Override
//        public void run() {
//            for (int i = 0; i < 1000; i++) {
//                System.out.println( name+" is running!");
//                Thread.yield(); // one one one one
//            }
//        }
//    }
//
//    public static void main(String[] args) throws InterruptedException {
//
//        Thread t1 = new Thread(new MyRunnable());
//        Thread t2 = new MyThread("A");
//        Thread t3 = new MyThread("B");
////        t3.setPriority(Thread.MAX_PRIORITY); //MAX_PRIORITY
////        t3.setPriority(10);
////        t1.setPriority(Thread.MIN_PRIORITY);
//        t1.start();
//        t2.start();
//        t3.start();
////        t1.join(); t2.join(); t3.join();
////        System.out.println("In main thread!");
//
////        t1.interrupt();
////        while (t1.isAlive()) {
////            System.out.println("T1 is aLIVE!");
////        }
//    }
//}
////////////////////////////////////////////////////////////////////////////////////////////////
//import java.util.Scanner;
//
//public class Main implements Runnable {
//    private boolean running;
//
//    public Main() {
//        this.running = true;
//    }
//
//    // Main function to start the stopwatch
//    public static void main(String[] args) {
//        Main stopWatch = new Main();
//        Thread thread = new Thread(stopWatch);
//        thread.start();
//
//        // Listen for Enter key to stop the stopwatch
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("Press Enter to stop the stopwatch...");
//        scanner.nextLine();  // Wait for user to press Enter
//        stopWatch.stop();
//        scanner.close();
//    }
//
//    // Run method for the thread
//    @Override
//    public void run() {
//        // this keeps running
//        int seconds = 0;
//        try {
//            while (running) {
//                System.out.printf("Time elapsed: %d seconds%n", seconds);
//                Thread.sleep(1000); // Wait for 1 second
//                seconds++;
//            }
//        } catch (InterruptedException e) {
//            System.out.println("Stopwatch interrupted.");
//        }
//    }
//
//    // Method to stop the stopwatch
//    public void stop() {
//        this.running = false;
//        System.out.println("Stopwatch stopped.");
//    }
//}
/////////////////////////////////////////////////////////////////////////////////
//import java.util.Scanner;
//
//public class Main {
//    public static void main(String[] args) throws InterruptedException {
//        Scanner scanner = new Scanner(System.in);
//
//        // Take dimensions of the matrices
//        System.out.print("Enter the number of rows: ");
//        int rows = scanner.nextInt();
//        System.out.print("Enter the number of columns: ");
//        int columns = scanner.nextInt();
//
//        // Initialize the matrices
//        int[][] matrix1 = new int[rows][columns];
//        int[][] matrix2 = new int[rows][columns];
//        int[][] result = new int[rows][columns];
//
//        // Input for the first matrix
//        System.out.println("Enter the elements of the first matrix:");
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                matrix1[i][j] = scanner.nextInt();
//            }
//        }
//
//        // Input for the second matrix
//        System.out.println("Enter the elements of the second matrix:");
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                matrix2[i][j] = scanner.nextInt();
//            }
//        }
//
//        // Create and start threads to add each row
//        Thread[] threads = new Thread[rows];
//        for (int i = 0; i < rows; i++) {
//            int row = i;  // Capture row index for the thread
//            threads[i] = new Thread(() -> addRow(matrix1, matrix2, result, row, columns));
//            threads[i].start();
//        }
//
//        // Wait for all threads to finish
//        for (Thread thread : threads) {
//            thread.join();
//        }
//
//        // Display the result matrix
//        System.out.println("Resultant Matrix after addition:");
//        for (int i = 0; i < rows; i++) {
//            for (int j = 0; j < columns; j++) {
//                System.out.print(result[i][j] + " ");
//            }
//            System.out.println();
//        }
//
//        scanner.close();
//    }
//
//    // Method to add corresponding rows of the matrices
//    private static void addRow(int[][] matrix1, int[][] matrix2, int[][] result, int row, int columns) {
//        for (int j = 0; j < columns; j++) {
//            result[row][j] = matrix1[row][j] + matrix2[row][j];
//        }
//    }
//}
////////////////////////////////////////////////////////////////////////////////////////////////////////////


//// java program to demonstrate
//// use of semaphores Locks
//import java.util.concurrent.*;
//
////A shared resource/class.
//class Shared
//{
//    static int count = 0;
//}
//
//
//class MyThread extends Thread
//{
//    Semaphore sem;
//    String threadName;
//    public MyThread(Semaphore sem, String threadName)
//    {
//        super(threadName);
//        this.sem = sem;
//        this.threadName = threadName;
//    }
//
//    @Override
//    public void run() {
//
//        // run by thread A
//        if(this.getName().equals("A"))
//        {
//            System.out.println("Starting " + threadName);
//            try
//            {
//                // First, get a permit.
//                System.out.println(threadName + " is waiting for a permit.");
//
//                // acquiring the lock
//                sem.acquire();
//
//                System.out.println(threadName + " gets a permit.");
//
//                // Now, accessing the shared resource.
//                // other waiting threads will wait, until this
//                // thread release the lock
//                for(int i=0; i < 5; i++)
//                {
//                    Shared.count++;
//                    System.out.println(threadName + ": " + Shared.count);
//
//                    // Now, allowing a context switch -- if possible.
//                    // for thread B to execute
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException exc) {
//                System.out.println(exc);
//            }
//
//            // Release the permit.
//            System.out.println(threadName + " releases the permit.");
//            sem.release();
//        }
//
//        // run by thread B
//        else
//        {
//            System.out.println("Starting " + threadName);
//            try
//            {
//                // First, get a permit.
//                System.out.println(threadName + " is waiting for a permit.");
//
//                // acquiring the lock
//                sem.acquire();
//
//                System.out.println(threadName + " gets a permit.");
//
//                // Now, accessing the shared resource.
//                // other waiting threads will wait, until this
//                // thread release the lock
//                for(int i=0; i < 5; i++)
//                {
//                    Shared.count--;
//                    System.out.println(threadName + ": " + Shared.count);
//
//                    // Now, allowing a context switch -- if possible.
//                    // for thread A to execute
//                    Thread.sleep(1000);
//                }
//            } catch (InterruptedException exc) {
//                System.out.println(exc);
//            }
//            // Release the permit.
//            System.out.println(threadName + " releases the permit.");
//            sem.release();
//        }
//    }
//}
//
//// Driver class
//public class Main
//{
//    public static void main(String args[]) throws InterruptedException
//    {
//        // creating a Semaphore object
//        // with number of permits 1
//        Semaphore sem = new Semaphore(1);
//
//        // creating two threads with name A and B
//        // Note that thread A will increment the count
//        // and thread B will decrement the count
//        MyThread mt1 = new MyThread(sem, "A");
//        MyThread mt2 = new MyThread(sem, "B");
//
//        // stating threads A and B
//        mt1.start();
//        mt2.start();
//
//        // waiting for threads A and B
//        mt1.join();
//        mt2.join();
//
//        // count will always remain 0 after
//        // both threads will complete their execution
//        System.out.println("count: " + Shared.count);
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//





/////////////////////////////////////////////////////////////////////////////
import java.io.*;
import java.util.*;
import java.util.concurrent.Semaphore;

class Car implements Runnable {
    private final int ID;
    private final int arrivalTime;
    private final int parkDuration;
    private final Semaphore sem;
    private final String Gate;
    private final ParkingLot parkingLot;

    public Car(int id, int arrivalTime, int parkDuration, Semaphore sem, String Gate, ParkingLot parkingLot) {
        this.ID = id;
        this.arrivalTime = arrivalTime;
        this.parkDuration = parkDuration;
        this.sem = sem;
        this.Gate = Gate;
        this.parkingLot = parkingLot;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(arrivalTime * 1000L);
            System.out.println("Car " + ID + " from " + Gate + " arrived at time " + arrivalTime);

            long startWaitingTime = System.currentTimeMillis();
//            if (parkingSemaphore.tryAcquire()) {
//                // aqiring successful
//                long waitedTime = (System.currentTimeMillis() - startWaitingTime) / 1000;
//                parkingLot.parkCar(gateName, carId);
//                System.out.println("Car " + carId + " from " + gateName + " parked after waiting for " + waitedTime + " units of time.");
//
//                Thread.sleep(parkingDuration * 1000L);
//
//                parkingLot.leaveCar(gateName, carId);
//                parkingSemaphore.release();
//            } else {
            sem.acquire();
                long wait_time = (System.currentTimeMillis() - startWaitingTime) / 1000;
                if(wait_time!=0)System.out.println("Car " + ID + " from " + Gate + " waiting for a spot.");
                parkingLot.parkCar(Gate, ID);
                if(wait_time!=0)System.out.println("Car " + ID + " from " + Gate + " parked after waiting for " + wait_time + " units of time.");

                // sleep for the duration
                Thread.sleep(parkDuration * 1000L);

                //  leaves
                parkingLot.leaveCar(Gate, ID);
            sem.release();
//            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Car " + ID + " from " + Gate + " interrupted.");
        }
    }
}


class ParkingLot {
    private int Gates;
    private int occupied = 0;
    private int totalServed = 0;
    private final int[] gateCounts;

    // Constructor to initialize the number of gates
    public ParkingLot(int Gates) {
        this.Gates = Gates;
        this.gateCounts = new int[Gates+1]; // Initialize gateCounts array based on Gates
    }

    public synchronized void parkCar(String Gate, int carId) {
        occupied++;
        totalServed++;
        String numberonly = Gate.replaceAll("[^0-9]", "").trim();
        int gt = Integer.parseInt(numberonly); // Corrected parse method

            gateCounts[gt]++; // Increment the count for the given gate

        System.out.println("Car " + carId + " from " + Gate + " parked. (Parking Status: " + occupied + " spots occupied)");
    }

    public synchronized void leaveCar(String gateName, int carId) {
        occupied--;
        System.out.println("Car " + carId + " from " + gateName + " left after parking. (Parking Status: " + occupied + " spots occupied)");
    }

    public void endprint() {
        System.out.println("Total Cars Served: " + totalServed);
        System.out.println("Current Cars in Parking: " + occupied);
        System.out.println("Details:");
        for (int i=1;i<=Gates;i++) {
            System.out.println("- Gate " + i + " served " + gateCounts[i] + " cars.");
        }
    }
}


public class Main {
    public static void main(String[] args) {
        int spots=4;
        int Gates=3;
        Semaphore parkingSemaphore = new Semaphore(spots); //4 here
        ParkingLot parkingLot = new ParkingLot(Gates);
    
        List<Thread> carThreads = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("car_schedule.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] details = line.split(", ");
                String gateName = details[0]; // Gate #
//                System.out.println(gateName);

                int carId = Integer.parseInt(details[1].split(" ")[1]);
                int arrivalTime = Integer.parseInt(details[2].split(" ")[1]);
                int parkingDuration = Integer.parseInt(details[3].split(" ")[1]);

                Car car = new Car(carId, arrivalTime, parkingDuration, parkingSemaphore, gateName, parkingLot);
                Thread carThread = new Thread(car);
                carThreads.add(carThread);
                carThread.start();
            }
        } catch (IOException e) {
            System.err.println("Error reading car schedule: " + e.getMessage());
        }

        for (Thread carThread : carThreads) {
            try {
                carThread.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        parkingLot.endprint();
    }
}
