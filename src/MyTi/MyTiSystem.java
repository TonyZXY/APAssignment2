package MyTi;


import java.util.*;


import MyTi.TravelPass.*;
import MyTi.TravelPassHistory.TravelPassHistory;

/**
 * Project APAssignment2
 * Created by TonyZheng on 24/9/16.
 */
public class MyTiSystem {
    private void setZoneOneOneDayPassPrice(double zoneOneOneDayPassPrice) {
        this.zoneOneOneDayPassPrice = zoneOneOneDayPassPrice;
    }

    private void setZoneOneTwoHoursPassPrice(double zoneOneTwoHoursPassPrice) {
        this.zoneOneTwoHoursPassPrice = zoneOneTwoHoursPassPrice;
    }

    private void setZoneTwoOneDayPassPrice(double zoneTwoOneDayPassPrice) {
        this.zoneTwoOneDayPassPrice = zoneTwoOneDayPassPrice;
    }

    private void setZoneTwoTwoHoursPassPrice(double zoneTwoTwoHoursPassPrice) {
        this.zoneTwoTwoHoursPassPrice = zoneTwoTwoHoursPassPrice;
    }

    private static double zoneOneOneDayPassPrice = 6.9;
    private static double zoneOneTwoHoursPassPrice = 3.5;
    private static double zoneTwoOneDayPassPrice = 9.8;
    private static double zoneTwoTwoHoursPassPrice = 5.0;

    private void mainMenu() { //main menu
        System.out.println("Welcome to MyTi");
        System.out.println("1. Buy a travel pass");
        System.out.println("2. Charge my MyTi");
        System.out.println("3. Show remaining credit");
        System.out.println("4. Take a Journey using a MyTi card");
        System.out.println("5. Show Card Info");
        System.out.println("6. Order a travel pass");
        System.out.println("** Order travel pass is for Assignment testing   **");
        System.out.println("** System can automatic generate time when using **");
        System.out.println("7. Admin mod");
        System.out.println("0. Quit");
        System.out.println("Choose an option:");
    }

    private void purchaseMenu() { //option 1
        System.out.println("What time period");
        System.out.println("1. 2 Hours");
        System.out.println("2. All Day");
        System.out.println("0. Cancel");
        System.out.println("Your option:");
    }

    private void zoneMenu() { //option 1
        System.out.println("Which zone: ");
        System.out.println("1. Zone 1");
        System.out.println("2. Zone 1 and 2");
        System.out.println("3. Select by MyTi.Station");
        System.out.println("0. Cancel");
        System.out.println("Your option:");
    }

    private void admin() {
        System.out.println("1. Print all Journeys made using all MyTi cards");
        System.out.println("2. Show MyTi.Station statistics");
        System.out.println("3. Add a new User");
//        System.out.println("4. Create a new MyTi Card");
//        System.out.println("5. Attach a MyTi Card to a User");
        System.out.println("5. Change Rate");
        System.out.println("6. Change Current price");
        System.out.println("7. Change Current MyTi.Station");
        System.out.println("0. Exit");
        System.out.println("Your option:");
    }

    private void cardInfo() {
        System.out.println("1. Show recent 10 travel history");
        System.out.println("2. Show recent 10 TopUp history");
        System.out.println("3. Show card Info");
        System.out.println("0. Exit");
        System.out.println("Your option:");
    }

    private void journeyMenu() {
        System.out.println("1. Take journey Using System Time");
        System.out.println("2. Take journey Using Time input");
        System.out.println("** Option 2 is for Assignment testing **");
        System.out.println("0. Exit");
    }

    private void cardInfoMenu() {
        System.out.println("Enter card id:");
        String id = new Scanner(System.in).nextLine();
        double balance = UsersData.checkUserID(id);
        if (balance == -1) {
            System.err.println("Card ID not Found!");
            menuRun();
        } else {
            runCardInfoMenu(id);
        }
    }

    private void runCardInfoMenu(String id) { //card info menu
        int m;
        try {
            do {
                cardInfo();
                m = new Scanner(System.in).nextInt();
                switch (m) {
                    case 1:
                        recent10TravelHistory(id);
                        break;
                    case 2:
                        recent10TopUpHistory(id);
                        break;
//                    case 3:
//                        showMoreInfo(id);
//                        break;
                    case 0:
                        menuRun();
                        break;
                    default:
                        System.err.println("Invalid Input, Try again.");
                        runCardInfoMenu(id);
                }
            } while (m != 0);
        } catch (Exception e) {
            System.err.println("Invalid Input, Try again.");
            runCardInfoMenu(id);
        }
    }

    private void recent10TopUpHistory(String id) {
        int i = UsersData.users.get(id).topUpHistorySize();
        if (i > 10) {
            for (int x = 0; x < 10; x++) {
                double balance = UsersData.users.get(id).getTopUpHistories().get(i - x - 1).getBalance();
                Date date = UsersData.users.get(id).getTopUpHistories().get(i - x - 1).getDate();
                System.out.println("TopUp $" + balance + ", at Time:" + date);
            }
            menuRun();
        } else {
            for (int x = i; x > 0; x--) {
                double balance = UsersData.users.get(id).getTopUpHistories().get(x - 1).getBalance();
                Date date = UsersData.users.get(id).getTopUpHistories().get(x - 1).getDate();
                System.out.println("TopUp $" + balance + ", at Time:" + date);
            }
            menuRun();
        }
    }

    private String generateType(int t) {
        String type = null;
        if (t == 1) {
            type = "Zone One 2 Hours Pass";
        }
        if (t == 3) {
            type = "Zone Two 2 Hours Pass";
        }
        if (t == 2) {
            type = "Zone One Day Pass";
        }
        if (t == 4) {
            type = "Zone Two Day Pass";
        }
        return type;
    }

    private void recent10TravelHistory(String id) {
        int i = UsersData.users.get(id).historySize();
        if (i > 10) {
            System.out.println("Your recent 10 travel pass history:");
            System.out.println("Time                Type");
            for (int x = 0; x < 10; x++) {
                Date date = UsersData.users.get(id).getHistory().get(i - x - 1).getCalendar().getTime();
                int tT = UsersData.users.get(id).getHistory().get(i - x - 1).getTicketType();

                String type = generateType(tT);
                System.out.println(date + "     " + type);
            }
        } else {
            System.out.println("Your recent 10 travel pass history:");
            System.out.println("Time                Type");
            for (int y = i; y > 0; y--) {
                Date date = UsersData.users.get(id).getHistory().get(y - 1).getCalendar().getTime();
                int tT = UsersData.users.get(id).getHistory().get(y - 1).getTicketType();
                String type = generateType(tT);
                System.out.println(date + "     " + type);
            }
        }
    }

    private void adminMenu() {
        System.out.println("Enter Admin username");
        String username = new Scanner(System.in).nextLine();
        System.out.println("Enter Admin Password");
        String password = new Scanner(System.in).nextLine();
        boolean valid = UsersData.checkAdmin(username, password);
        if (valid) {
            runAdminMenu();
        } else {
            System.err.println("Invalid Admin user, Exiting");
            menuRun();
        }
    }

    private void runAdminMenu() {
        int m;
        try {
            do {
                admin();
                m = new Scanner(System.in).nextInt();
                switch (m) {
                    case 1:
                        printAllJorneys();
                        break;
                    case 2:
                        showStationStatistics();
                        break;
                    case 3:
                        addNewUser();
                        break;
                    case 5:
                        changeRate();
                        break;
                    case 6:
                        changePrice();
                        break;
                    case 7:
                        changeCurrentStation();
                        break;
                    case 0:
                        menuRun();
                        break;
                    default:
                        printBlackLine();
                        System.out.println("Invalid Input. Try again.");
                        runAdminMenu();
                }
            } while (m != 0);
        } catch (Exception e) {
            printBlackLine();
            System.out.println("Invalid Input, Try again.");
            runAdminMenu();
        }
    }

    //*************************change Price *****************************

    private void changeRate() {
        System.out.println("Current Rate is");
        System.out.println("1. Adult Rate : " + UsersData.getArate());
        System.out.println("2. Junior Rate: " + UsersData.getJrate());
        System.out.println("3. Senior Rate: " + UsersData.getSrate());
        System.out.println("4. Sunday Rate: " + UsersData.getSSrate());
        try {
            System.out.println("Which to Change");
            System.out.println("0. Exit");
            int m = new Scanner(System.in).nextInt();
            switch (m) {
                case 1:
                    setAR();
                    break;
                case 2:
                    setJR();
                    break;
                case 3:
                    setSR();
                    break;
                case 4:
                    setSSR();
                    break;
                case 0:
                    runAdminMenu();
                    break;
                default:
                    System.out.println("Invalid input. Try again");
                    changeRate();
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
            changeRate();
        }
    }

    private void setAR() {
        try {
            System.out.println("Enter New Adult Rate:");
            double rate = Double.parseDouble(new Scanner(System.in).next());
            UsersData.setArate(rate);
            System.out.println("Current Adult Rate:" + UsersData.getArate());
            runAdminMenu();
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
            setAR();
        }
    }

    private void setJR() {
        try {
            System.out.println("Enter New Junior Rate:");
            double rate = Double.parseDouble(new Scanner(System.in).next());
            UsersData.setJrate(rate);
            System.out.println("Current Adult Rate:" + UsersData.getJrate());
            runAdminMenu();
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
            setJR();
        }
    }

    private void setSR() {
        try {
            System.out.println("Enter New Senior Rate:");
            double rate = Double.parseDouble(new Scanner(System.in).next());
            UsersData.setSrate(rate);
            System.out.println("Current Adult Rate:" + UsersData.getSrate());
            runAdminMenu();
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
            setSR();
        }
    }

    private void setSSR() {
        try {
            System.out.println("Enter New Senior Sunday Rate:");
            double rate = Double.parseDouble(new Scanner(System.in).next());
            UsersData.setSSrate(rate);
            System.out.println("Current Adult Rate:" + UsersData.getSSrate());
            runAdminMenu();
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
            setSSR();
        }
    }

    private void changePrice() {  //change price of ticket
        System.out.println("Current price is");
        System.out.println("1.Two Hours Zone 1:" + zoneOneTwoHoursPassPrice);
        System.out.println("2.Two Hours Zone 2:" + zoneTwoTwoHoursPassPrice);
        System.out.println("3.One Day   Zone 1:" + zoneOneOneDayPassPrice);
        System.out.println("4.One Day   Zone 2:" + zoneTwoOneDayPassPrice);
        try {
            System.out.println("Witch to change:");
            System.out.println("Or enter 0 to Exit");
            int m = new Scanner(System.in).nextInt();
            switch (m) {
                case 1:
                    set1();
                    break;
                case 2:
                    set2();
                    break;
                case 3:
                    set3();
                    break;
                case 4:
                    set4();
                    break;
                case 0:
                    runAdminMenu();
                    break;
                default:
                    System.out.println("Invalid input. Try again");
                    changePrice();
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
            changePrice();
        }
    }

    private void set1() {
        try {
            System.out.println("Enter New Zone 1, 2 hours Price:");
            double price = Double.parseDouble(new Scanner(System.in).next());
            setZoneOneTwoHoursPassPrice(price);
            System.out.println("Current Zone 1, 2 hours Price:" + zoneOneTwoHoursPassPrice);
            runAdminMenu();
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
            set1();
        }
    }

    private void set2() {
        try {
            System.out.println("Enter New Zone 2, 2 hours Price:");
            double price = Double.parseDouble(new Scanner(System.in).next());
            setZoneTwoTwoHoursPassPrice(price);
            System.out.println("Current Zone 2, 2 hours Price:" + zoneTwoTwoHoursPassPrice);
            runAdminMenu();
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
            set2();
        }
    }

    private void set3() {
        try {
            System.out.println("Enter New Zone 1, One Day Price:");
            double price = Double.parseDouble(new Scanner(System.in).next());
            setZoneOneOneDayPassPrice(price);
            System.out.println("Current Zone 1, One Day Price:" + zoneOneOneDayPassPrice);
            runAdminMenu();
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
            set3();
        }
    }

    private void set4() {
        try {
            System.out.println("Enter New Zone 2, One Day Price:");
            double price = Double.parseDouble(new Scanner(System.in).next());
            setZoneTwoOneDayPassPrice(price);
            System.out.println("Current Zone 2, One Day Price:" + zoneTwoOneDayPassPrice);
            runAdminMenu();
        } catch (Exception e) {
            System.out.println("Invalid input. Try again");
            set4();
        }
    }
    //*****************************  End Change Price *********************

    //*****************************  Print All Journeys ********************
    private void printAllJorneys() {
        System.out.println("        ID       Ticket Type    Start MyTi.Station     Destination MyTi.Station    Price   Date");
        for (int i = 0; i < TravelPassHistory.travelPassHistory.size(); i++) {
            String ticketType = null;
            String id = TravelPassHistory.travelPassHistory.get(i).getId();
            int type = TravelPassHistory.travelPassHistory.get(i).getTravelPass().getTicketType();

            String startStation = TravelPassHistory.travelPassHistory.get(i).getTravelPass().getStartName();
            String destinationStation = TravelPassHistory.travelPassHistory.get(i).getTravelPass().getEndName();
            Calendar date = TravelPassHistory.travelPassHistory.get(i).getTravelPass().getCalendar();
            double price = TravelPassHistory.travelPassHistory.get(i).getTravelPass().getPrice();
            if (type == 1) {
                ticketType = "Zone 1   Two Hours";
            }
            if (type == 3) {
                ticketType = "Zone 1+2 Two Hours";
            }
            if (type == 2) {
                ticketType = "Zone 1   One Day";
            }
            if (type == 4) {
                ticketType = "Zone1+2  One Day";
            }
            System.out.println(i + " " + id + " " + ticketType + " " + startStation + " " + destinationStation + " " + price + "  " + date.getTime());
        }
        printBlackLine();
        System.out.println("Press Any key to continue");
        new Scanner(System.in).next();
        runAdminMenu();
    }

    private HashMap<String, Integer> stationStartStatistics = new HashMap<>();
    private HashMap<String, Integer> stationEndStatistics = new HashMap<>();

    private void addStationName() {
        for (int i = 0; i < UsersData.stationsName.size(); i++) {
            String stationName = UsersData.stationsName.get(i);
            stationStartStatistics.put(stationName, 0);
            stationEndStatistics.put(stationName, 0);
        }
    }

    private void addStationStart() {
        for (int i = 0; i < TravelPassHistory.travelPassHistory.size(); i++) {
            String stationName = TravelPassHistory.travelPassHistory.get(i).getTravelPass().getStartName();
            stationStartStatistics.put(stationName, stationStartStatistics.get(stationName) + 1);
        }
    }

    private void addStationEnd() {
        for (int i = 0; i < TravelPassHistory.travelPassHistory.size(); i++) {
            String stationName = TravelPassHistory.travelPassHistory.get(i).getTravelPass().getEndName();
            stationEndStatistics.put(stationName, stationEndStatistics.get(stationName) + 1);
        }
    }

    private void showStationStatistics() {
        addStationName();
        addStationStart();
        addStationEnd();
        for (int i = 0; i < UsersData.stationsName.size(); i++) {
            String stationName = UsersData.stationsName.get(i);
            int start = stationStartStatistics.get(stationName);
            int end = stationEndStatistics.get(stationName);
            System.out.println(stationName + ": " + start + " journeys start and " + end + "journeys end here");
        }
    }

    //*****************************  End Print All Journeys  ***************************

    //***************************** change station ********************
    private void changeCurrentStation() {
        System.out.println("Current MyTi.Station is: " + thisStop);
        System.out.println("MyTi.Station Name    :  Zone");
        for (int i = 0; i < UsersData.stationsName.size(); i++) {
            System.out.println(UsersData.stationsName.get(i));
        }
        System.out.println("Enter Changed MyTi.Station: ");
        String station = new Scanner(System.in).nextLine();
        boolean valid = UsersData.checkStation(station);
        if (valid) {
            setThisStop(station);
            System.out.println("Current station now is: " + thisStop);
            runAdminMenu();
        } else {
            System.out.println("Invalid station name, Try again");
            runAdminMenu();
        }
    }

    //*************************** Add MyTi.Users *****************
    private void addNewUser() {
        String id;
        String userName;
        String email;
        char type;
        try {
            char m;
            do {
                System.out.println("Enter User id");
                System.out.println("Enter (Q) to Quit");
                id = new Scanner(System.in).nextLine();
                m = new Scanner(System.in).next().charAt(0);
                boolean valid = UsersData.checkUser(id);
                if (valid) {
                    System.out.println("User id is Used, try other ID");
//                    System.out.println("Sorry, that card already has a user; only 1 user per card is allowed");
                    addNewUser();
                }
                System.out.println("Enter User Name");
                userName = new Scanner(System.in).nextLine();
                System.out.println("Enter User E-mail Address");
                email = new Scanner(System.in).nextLine();
                System.out.println("Enter User Type");
                System.out.println("(A)Adult,(C)Conssion,(S)Student");
                type = new Scanner(System.in).next().charAt(0);
                UsersData.addNewUser(id, userName, email, type);
            } while (m != 'Q');
            runAdminMenu();
        } catch (Exception e) {
            System.out.println("Invalid Input, Try again.");
            addNewUser();
        }
    }

//    private Scanner keyPad = new Scanner(System.in);

    public void menuRun() {
        int m;
        try {
            do {
                mainMenu();
                m = new Scanner(System.in).nextInt();
                switch (m) {
                    case 1:
                        travelPassPurchase();
                        break;
                    case 2:
                        chargeMyTiCard();
                        break;
                    case 3:
                        showCreditMenu();
                        break;
                    case 4:
                        takeJourneyMenu();
                        break;
                    case 5:
                        cardInfoMenu();
                        break;
                    case 6:
                        travelPassOrdered();
                        break;
                    case 7:
                        adminMenu();
                        break;
//                    case 0:
//                        waitingForCustomer();
//                        break;
                    default:
                        printBlackLine();
                        System.out.println("Invalid Input, Try again.");
                        menuRun();
                }
            } while (m != 0);
        } catch (InputMismatchException e) {
            printBlackLine();
            System.out.println("Invalid Input, Try again.");
            menuRun();
        }
    }

    private void travelPassPurchase() { //check user info
        String userID;
        printBlackLine();
        System.out.println("Which card ID: ");
        userID = new Scanner(System.in).nextLine();
        double balance = UsersData.checkUserID(userID);
        if (balance != -1) {
            char type = UsersData.users.get(userID).getType();
            double rate = UsersData.getRate(userID);
            travelPassPurchaseMenu(userID, balance, rate, type);
        } else {
            printBlackLine();
            System.err.println("User ID not found. Try again.");
            menuRun();
        }
    }

    private void travelPassOrdered() { //check user info and make order
        String id;
        printBlackLine();
        System.out.println("Which card ID: ");
        id = new Scanner(System.in).nextLine();
        double balance = UsersData.checkUserID(id);
        if (balance != -1) {
            char type = UsersData.users.get(id).getType();
            double rate = UsersData.getRate(id);
            travelPassOrderedMenu(id, balance, type, rate);
        } else {
            printBlackLine();
            System.out.println("User ID not found. Try again");
            menuRun();
        }
    }

    //******************************* Order Travel Pass    select time by customer  *************************
    private void travelPassOrderedMenu(String id, double balance, char type, double rate) {  // travel Pass ORDER menu
        char duration = 'O';                                                                 // Not Purchase
        int zone = 0;
        try {
            int d;
            int z;
            do {
                purchaseMenu();
                d = new Scanner(System.in).nextInt();
                switch (d) {
                    case 1:
                        duration = 'H';
                        break;
                    case 2:
                        duration = 'D';
                        break;
                    case 0:
                        menuRun();
                        break;
                    default:
                        System.out.println("Invalid input, try again!");
                        travelPassOrderedMenu(id, balance, type, rate);
                }
            } while (d != 0);
            do {
                zoneMenu();
                z = new Scanner(System.in).nextInt();
                switch (z) {
                    case 1:
                        zone = 1;
                        break;
                    case 2:
                        zone = 2;
                        break;
                    case 3:
                        selectByStation(id, balance, type, rate, duration);
                        break;
                    case 0:
                        menuRun();
                        break;
                    default:
                        System.out.println("Invalid input,try again!");
                        travelPassOrderedMenu(id, balance, type, rate);
                }
            } while (z != 0);
            travelPassOrder(id, balance, type, rate, duration, zone);
        } catch (Exception e) {
            System.out.println("Invalid input, try again!");
            travelPassOrderedMenu(id, balance, type, rate);
        }
    }

    private void selectByStation(String id, double balance, char type, double rate, char duration) {
        try {
            System.out.println("MyTi.Station Name    :  Zone");
            for (int i = 0; i < UsersData.stationsName.size(); i++) {
                System.out.println(UsersData.stationsName.get(i));
            }
            System.out.println("Please enter station name:");
            String destnationStop = new Scanner(System.in).nextLine();
            int thisZone = UsersData.station.get(thisStop).getZone();
            int destnationZone = UsersData.station.get(destnationStop).getZone();
            if (thisZone == 1 && destnationZone == 1) {
                int zone = 1;
                travelPassOrder(id, balance, type, rate, duration, zone);
            } else if ((thisZone == 1 && destnationZone == 2) || (thisZone == 2 && destnationZone == 1) || (thisZone == 2 && destnationZone == 2)) {
                int zone = 2;
                travelPassOrder(id, balance, type, rate, duration, zone);
            }
        } catch (Exception e) {
            printBlackLine();
            System.out.println("Invalid input,try again");
            selectByStation(id, balance, type, rate, duration);
        }
    }

    private void travelPassOrder(String id, double balance, char type, double rate, char duration, int zone) {
        try {
            System.out.println("which day you would like to travel");
            System.out.println("Enter the day you would like to travel:(day of the month)");
            int day = new Scanner(System.in).nextInt();
//            System.out.println("Enter the day you would like to travel:(Day of the Week");
//            String day = new Scanner(System.in).nextLine();
            printBlackLine();
            System.out.println("Enter the TIME you would like to travel:(HHMM)");
            int time = new Scanner(System.in).nextInt();
            int hour = time / 100;
            int min = time % 100;
            if (min / 60 >= 1 || hour / 24 >= 1) {
                throw new Exception("Invalid Time input.");
            }
            printBlackLine();
            Calendar date = Calendar.getInstance();
            date.set(Calendar.DAY_OF_MONTH, day);
//            date.set(Calendar.DAY_OF_WEEK.day);
            date.set(Calendar.HOUR_OF_DAY, hour);
            date.set(Calendar.MINUTE, min);
            switchPurchase(id, balance, type, rate, duration, zone, date);
        } catch (Exception e) {
            System.err.println(e);
            System.out.println("Invalid input,Try again");
            travelPassOrder(id, balance, type, rate, duration, zone);
        }
    }

    private void switchPurchase(String id, double balance, char type, double rate, char duration, int zone, Calendar date) {
        try {
            if (duration == 'H' && zone == 1) {
                if (balance - zoneOneTwoHoursPassPrice * rate < 0) {
                    throw new NoEnoughFundException("No enough fund, please TopUp first.");
                } else {
                    UsersData.users.get(id).purchase(zoneOneTwoHoursPassPrice * rate);
                    UsersData.users.get(id).getHistory().add(new TravelPass(date, 1, thisStop, type, zoneOneTwoHoursPassPrice * rate));
                    TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 1, thisStop, type, zoneOneTwoHoursPassPrice * rate)));
                    System.out.println("You successfully purchase 2 hours Zone 1 travel Pass.");
                    printBlackLine();
                    menuRun();
                }
            } else if (duration == 'H' && zone == 2) {
                if (balance - zoneTwoTwoHoursPassPrice * rate < 0) {
                    throw new NoEnoughFundException("No enough fund, please TopUp first.");
                } else {
                    UsersData.users.get(id).purchase(zoneTwoTwoHoursPassPrice * rate);
                    UsersData.users.get(id).getHistory().add(new TravelPass(date, 3, thisStop, type, zoneTwoTwoHoursPassPrice * rate));
                    TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 3, thisStop, type, zoneTwoTwoHoursPassPrice * rate)));
                    System.out.println("You successfully purchase 2 hours Zone 2 travel Pass.");
                    printBlackLine();
                    menuRun();
                }
            } else if (duration == 'D' && zone == 1) {
                if (balance - zoneOneOneDayPassPrice * rate < 0) {
                    throw new NoEnoughFundException("No enough fund, please TopUp first.");
                } else {
                    UsersData.users.get(id).purchase(zoneOneOneDayPassPrice * rate);
                    UsersData.users.get(id).getHistory().add(new TravelPass(date, 2, thisStop, type, zoneOneOneDayPassPrice * rate));
                    TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 2, thisStop, type, zoneOneOneDayPassPrice * rate)));
                    System.out.println("You successfully purchase 1 day Zone 1 travel Pass.");
                    printBlackLine();
                    menuRun();
                }
            } else if (duration == 'D' && zone == 2) {
                if (balance - zoneTwoOneDayPassPrice * rate < 0) {
                    throw new NoEnoughFundException("No enough fund, please TopUp first.");
                } else {
                    UsersData.users.get(id).purchase(zoneTwoOneDayPassPrice * rate);
                    UsersData.users.get(id).getHistory().add(new TravelPass(date, 4, thisStop, type, zoneTwoOneDayPassPrice * rate));
                    TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 4, thisStop, type, zoneTwoOneDayPassPrice * rate)));
                    System.out.println("You successfully purchase 1 day Zone 2 travel Pass.");
                    printBlackLine();
                    menuRun();
                }
            }
        } catch (NoEnoughFundException err) {
            System.err.println(err);
            menuRun();
        }
    }
    //****************************  End Order Travel Pass **********************

    //**************************** this is for Purchase travel Pass     no time selection  ********
    private void purchaseTwoHoursPass(String userID, double balance, double rate, char type) { //zone choose (2hours)
        try {
            zoneMenu();
            int m = new Scanner(System.in).nextInt();
            switch (m) {
                case 1:
                    purchaseZoneOneTwoHoursPass(userID, balance, rate, type, null);
                    break;
                case 2:
                    purchaseZoneTwoTwoHoursPass(userID, balance, rate, type, null);
                    break;
                case 3:
                    selectByStationTwoHoursPass(userID, balance, rate, type);
                    break;
                case 0:
                    mainMenu();
                    break;
                default:
                    printBlackLine();
                    System.out.println("Invalid Input, Try again.");
                    purchaseTwoHoursPass(userID, balance, rate, type);
            }
        } catch (InputMismatchException e) {
            printBlackLine();
            System.out.println("Invalid Input, Try again.");
            purchaseTwoHoursPass(userID, balance, rate, type);
        }

    }

    private void travelPassPurchaseMenu(String id, double balance, double rate, char type) { //purchase choose pass type
        try {
            purchaseMenu();
            int m = new Scanner(System.in).nextInt();
            switch (m) {
                case 1:
                    purchaseTwoHoursPass(id, balance, rate, type);
                    break;
                case 2:
                    purchaseOneDayPass(id, balance, rate, type);
                    break;
                case 0:
                    menuRun();
                    break;
                default:
                    printBlackLine();
                    System.out.println("Invalid Input, Try again.");
                    travelPassPurchase();
            }
        } catch (InputMismatchException e) {
            printBlackLine();
            System.out.println("Invalid Input, Try again.");
            travelPassPurchaseMenu(id, balance, rate, type);
        }

    }

    private void purchaseZoneOneTwoHoursPass(String id, double balance, double rate, char type, String endName) { //make purchase of 2hours zone1
        try {
            double validPrice = checkValidTicketPrice(id, 1);
            double amount = zoneOneTwoHoursPassPrice * rate;
            if (validPrice == 10) {
                System.out.println("You already have a Travel Pass");
                menuRun();
            } else if (validPrice != 0) {
                if (amount - validPrice < 0) {
                    if (balance - amount < 0) {
                        throw new NoEnoughFundException("No enough Fund in your card");
                    } else {
                        UsersData.users.get(id).purchase(amount);
                        Calendar date = Calendar.getInstance();
                        UsersData.users.get(id).getHistory().add(new TravelPass(date, 1, thisStop, type, amount));  //add history
                        TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 1, thisStop, type, amount)));
                        System.out.println("You successfully purchase 2 hours Zone 1 travel Pass.");
                        printBlackLine();
                        menuRun();
                    }
                }
                if (amount - validPrice >= 0) {
                    if (balance - zoneOneOneDayPassPrice * rate + validPrice < 0) {
                        throw new NoEnoughFundException("No enough Fund in your card");
                    } else {
                        UsersData.users.get(id).purchase(zoneOneOneDayPassPrice * rate - validPrice);
                        Calendar date = Calendar.getInstance();
                        UsersData.users.get(id).getHistory().add(new TravelPass(date, 2, thisStop, type, zoneOneOneDayPassPrice * rate - validPrice));  //add history
                        TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 2, thisStop, type, zoneOneOneDayPassPrice * rate - validPrice)));
                        System.out.println("You successfully purchase 1 Day Zone 1 travel Pass.");
                        printBlackLine();
                        menuRun();
                    }
                }
            }
            if (validPrice == 0) {
                if (balance - (amount) < 0) {
                    printBlackLine();
                    throw new NoEnoughFundException("No enough Fund in your card");
                } else {
                    UsersData.users.get(id).purchase(amount);
                    Calendar date = Calendar.getInstance();
                    if (Objects.equals(endName, null)) {
                        UsersData.users.get(id).getHistory().add(new TravelPass(date, 1, thisStop, type, amount));  //add history
                        TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 1, thisStop, type, amount)));
                    } else {
                        UsersData.users.get(id).getHistory().add(new TravelPass(date, 1, thisStop, endName, type, amount));
                        TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 1, thisStop, endName, type, amount)));
                    }
                    System.out.println("You successfully purchase 2 hours Zone 1 travel Pass.");
                    printBlackLine();
                    menuRun();
                }
            }

        } catch (NoEnoughFundException e) {
            printBlackLine();
            System.err.println(e);
            menuRun();
        }
    }

    private void purchaseZoneTwoTwoHoursPass(String id, double balance, double rate, char type, String endName) { //make purchase of 2hours zone1&2
        try {
            double validPrice = checkValidTicketPrice(id, 2);
            double amount = zoneTwoTwoHoursPassPrice * rate;
            if (validPrice == 10) {
                System.out.println("You already have a Travel Pass");
                menuRun();
            } else if (amount - validPrice > 0) {
                if (balance - amount + validPrice < 0) {
                    throw new NoEnoughFundException("No enough Fund in your card");
                } else {
                    UsersData.users.get(id).purchase(amount - validPrice);
                    Calendar date = Calendar.getInstance();
                    UsersData.users.get(id).getHistory().add(new TravelPass(date, 2, thisStop, type, amount - validPrice));  //add history
                    TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 2, thisStop, type, amount - validPrice)));
                    System.out.println("You successfully purchase 2 Hours Zone 2 travel Pass.");
                    printBlackLine();
                    menuRun();
                }
            }
            if (amount - validPrice < 0) {
                if (balance - zoneTwoOneDayPassPrice * rate + validPrice < 0) {
                    throw new NoEnoughFundException("No enough Fund in your card");
                } else {
                    UsersData.users.get(id).purchase(zoneTwoOneDayPassPrice * rate - validPrice);
                    Calendar date = Calendar.getInstance();
                    UsersData.users.get(id).getHistory().add(new TravelPass(date, 2, thisStop, type, zoneTwoOneDayPassPrice * rate - validPrice));  //add history
                    TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 2, thisStop, type, zoneTwoOneDayPassPrice * rate - validPrice)));
                    System.out.println("You successfully purchase 1 Day Zone 2 travel Pass.");
                    printBlackLine();
                    menuRun();
                }
            }
            if (validPrice == 0) {
                if (balance - (zoneTwoTwoHoursPassPrice * rate) < 0) {
                    printBlackLine();
                    throw new NoEnoughFundException("No enough Fund in your card");
                } else {
                    UsersData.users.get(id).purchase(zoneTwoTwoHoursPassPrice * rate);
                    Calendar date = Calendar.getInstance();
                    if (Objects.equals(endName, null)) {
                        UsersData.users.get(id).getHistory().add(new TravelPass(date, 3, thisStop, type, zoneTwoTwoHoursPassPrice * rate)); //add history
                        TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 3, thisStop, type, zoneTwoTwoHoursPassPrice * rate)));
                    } else {
                        UsersData.users.get(id).getHistory().add(new TravelPass(date, 3, thisStop, endName, type, zoneTwoTwoHoursPassPrice * rate));
                        TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 3, thisStop, endName, type, zoneTwoTwoHoursPassPrice * rate)));
                    }
                    System.out.println("You successfully purchase 2 hours Zone 1 & Zone 2 travel Pass.");
                    printBlackLine();
                    menuRun();
                }
            }
        } catch (NoEnoughFundException err) {
            System.err.println(err);
            menuRun();
        }
    }

    private void purchaseOneDayPass(String id, double balance, double rate, char type) { //day pass menu

        try {
            zoneMenu();
            int m = new Scanner(System.in).nextInt();
            switch (m) {
                case 1:
                    purchaseZoneOneDayPass(id, balance, rate, type, null);
                    break;
                case 2:
                    purchaseZoneTwoDayPass(id, balance, rate, type, null);
                    break;
                case 3:
                    selectByStationDayPass(id, balance, rate, type);
                    break;
                case 0:
                    menuRun();
                    break;
                default:
                    printBlackLine();
                    System.out.println("Invalid Input, Try again.");
                    purchaseOneDayPass(id, balance, rate, type);
            }
        } catch (InputMismatchException e) {
            printBlackLine();
            System.out.println("Invalid Input, Try again.");
            purchaseOneDayPass(id, balance, rate, type);
        }

    }

    private void purchaseZoneOneDayPass(String id, double balance, double rate, char type, String endName) { //purchase
        try {
            double validPrice = checkValidTicketPrice(id, 3);
            double amount = zoneOneOneDayPassPrice * rate;
            if (validPrice == 10) {
                System.out.println("You already have a Travel Pass");
                menuRun();
            } else if (validPrice != 0) {
                if (balance - amount + validPrice < 0) {
                    throw new NoEnoughFundException("No enough Fund in your card");
                } else {
                    UsersData.users.get(id).purchase(amount - validPrice);
                    Calendar date = Calendar.getInstance();
                    UsersData.users.get(id).getHistory().add(new TravelPass(date, 2, thisStop, type, amount - validPrice));  //add history
                    TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 2, thisStop, type, amount - validPrice)));
                    System.out.println("You successfully purchase 1 Day Zone 1 travel Pass.");
                    printBlackLine();
                    menuRun();
                }
            }
            if (validPrice == 0) {
                if (balance - (zoneOneOneDayPassPrice * rate) < 0) {
                    throw new NoEnoughFundException("No enough Fund in your card");
                } else {
                    UsersData.users.get(id).purchase(zoneOneOneDayPassPrice * rate);
                    Calendar date = Calendar.getInstance();
                    if (Objects.equals(endName, null)) {
                        UsersData.users.get(id).getHistory().add(new TravelPass(date, 2, thisStop, type, zoneOneOneDayPassPrice * rate)); //add history
                        TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 2, thisStop, type, zoneOneOneDayPassPrice * rate)));
                    } else {
                        UsersData.users.get(id).getHistory().add(new TravelPass(date, 2, thisStop, endName, type, zoneOneOneDayPassPrice * rate));
                        TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 2, thisStop, endName, type, zoneOneOneDayPassPrice * rate)));
                    }
                    System.out.println("You successfully purchase 1 day Zone 1 travel Pass.");
                    printBlackLine();
                    menuRun();
                }
            }
        } catch (NoEnoughFundException err) {
            printBlackLine();
            System.err.println(err);
            menuRun();
        }
    }

    private void purchaseZoneTwoDayPass(String id, double balance, double rate, char type, String endName) { //purchase
        try {
            double validPrice = checkValidTicketPrice(id, 2);
            double amount = zoneOneOneDayPassPrice * rate;
            if (validPrice == 10) {
                System.out.println("You already have a Travel Pass");
                menuRun();
            } else if (validPrice != 0) {
                if (balance - amount + validPrice < 0) {
                    throw new NoEnoughFundException("No enough Fund in your card");
                } else {
                    UsersData.users.get(id).purchase(amount - validPrice);
                    Calendar date = Calendar.getInstance();
                    UsersData.users.get(id).getHistory().add(new TravelPass(date, 4, thisStop, type, amount - validPrice));  //add history
                    TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 4, thisStop, type, amount - validPrice)));
                    System.out.println("You successfully purchase 1 Day Zone 2 travel Pass.");
                    printBlackLine();
                    menuRun();
                }
            }
            if (validPrice == 0) {
                if (balance - (zoneTwoOneDayPassPrice * rate) < 0) {
                    throw new NoEnoughFundException("No enough Fund in your card");
                } else {
                    UsersData.users.get(id).purchase(zoneTwoOneDayPassPrice * rate);
                    Calendar date = Calendar.getInstance();
                    if (Objects.equals(endName, null)) {
                        UsersData.users.get(id).getHistory().add(new TravelPass(date, 4, thisStop, type, zoneTwoOneDayPassPrice * rate)); //add history
                        TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 4, thisStop, type, zoneTwoOneDayPassPrice * rate)));
                    } else {
                        UsersData.users.get(id).getHistory().add(new TravelPass(date, 4, thisStop, endName, type, zoneTwoOneDayPassPrice * rate));
                        TravelPassHistory.travelPassHistory.add(new TravelPassHistory(id, new TravelPass(date, 4, thisStop, endName, type, zoneTwoOneDayPassPrice * rate)));
                        //add travel pass history for admin to use
                    }
                    System.out.println("You successfully purchase 1 day Zone 1 & Zone 2 travel Pass.");
                    printBlackLine();
                    menuRun();
                }
            }
        } catch (NoEnoughFundException err) {
            printBlackLine();
            System.err.println(err);
            menuRun();
        }
    }

    private void selectByStationTwoHoursPass(String id, double balance, double rate, char type) { //select by station name 2 hours pass
        try {
            System.out.println("MyTi.Station Name    :  Zone");
            for (int i = 0; i < UsersData.stationsName.size(); i++) {
                System.out.println(UsersData.stationsName.get(i));
            }
            printBlackLine();
            System.out.println("Please enter station name:");
            String thisStop = "Central";
            String destnationStop = new Scanner(System.in).nextLine();
            int thisZone = UsersData.station.get(thisStop).getZone();
            int destnationZone = UsersData.station.get(destnationStop).getZone();
            if (thisZone == 1 && destnationZone == 1) {
                purchaseZoneOneTwoHoursPass(id, balance, rate, type, destnationStop);
            } else if ((thisZone == 1 && destnationZone == 2) || (thisZone == 2 && destnationZone == 1) || (thisZone == 2 && destnationZone == 2)) {
                purchaseZoneTwoTwoHoursPass(id, balance, rate, type, destnationStop);
            }

        } catch (Exception e) {
            printBlackLine();
            System.err.println("Invalid input, Try again");
            selectByStationTwoHoursPass(id, balance, rate, type);
        }
    }

    public void setThisStop(String thisStop) {
        this.thisStop = thisStop;
    }

    private String thisStop = "Central";

    private void selectByStationDayPass(String id, double balance, double rate, char type) { //select by station name day pass
        try {
            System.out.println("MyTi.Station Name    :  Zone");
            for (int i = 0; i < UsersData.stationsName.size(); i++) {
                System.out.println(UsersData.stationsName.get(i));
            }
            printBlackLine();
            System.out.println("Please enter station name:");
            String destnationStop = new Scanner(System.in).nextLine();
            int thisZone = UsersData.station.get(thisStop).getZone();
            int destnationZone = UsersData.station.get(destnationStop).getZone();
            if (thisZone == 1 && destnationZone == 1) {
                purchaseZoneOneDayPass(id, balance, rate, type, destnationStop);
            } else if ((thisZone == 1 && destnationZone == 2) || (thisZone == 2 && destnationZone == 1) || (thisZone == 2 && destnationZone == 2)) {
                purchaseZoneTwoDayPass(id, balance, rate, type, destnationStop);
            }
        } catch (Exception e) {
            printBlackLine();
            System.out.println("Invalid input, Try again");
            selectByStationDayPass(id, balance, rate, type);
        }
    }

    private double checkValidTicketPrice(String id, int type) {
        double price;
        try {
            int ticketType = UsersData.users.get(id).getHistory().get(UsersData.users.get(id).getHistory().size() - 1).getTicketType();
            Calendar ticketDate = UsersData.users.get(id).getHistory().get(UsersData.users.get(id).getHistory().size() - 1).getCalendar();
            Calendar date = Calendar.getInstance();
            if ((ticketType == 4 && ticketDate.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR)) || ticketType == 2 && ticketDate.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR) && type < 3) {
                price = 10;
            } else if (ticketType >= type && date.getTimeInMillis() - ticketDate.getTimeInMillis() < 7200000 && ticketDate.get(Calendar.DAY_OF_YEAR) == date.get(Calendar.DAY_OF_YEAR)) {
                price = 10;
            } else {
                price = UsersData.users.get(id).getHistory().get(UsersData.users.get(id).getHistory().size() - 1).getPrice();
            }
        } catch (Exception e) {
            price = 0;
        }
        return price;
    }
    //********************************  End of purchase travel Pass ************************


    //***************************** charge MyTi *****************************
    private void chargeMyTiCard() { //check user id before TopUp
        String userID;
        System.out.print("Which card ID: ");
        userID = new Scanner(System.in).nextLine();
        double balance = UsersData.checkUserID(userID);
        printBlackLine();
        if (balance != -1) {
            confirmMyTiTopUp(userID, balance);
        } else {
            printBlackLine();
            System.err.println("User ID not found. Try again.");
            menuRun();
        }
    }

    private void confirmMyTiTopUp(String id, double balance) {  //check if valid
        try {
            System.out.println("Your current balance is: " + balance + "$");
            System.out.println("How much to TopUp:");
            double amount = new Scanner(System.in).nextInt();
            printBlackLine();
            if (amount < 0) {
                throw new TopUpException("Invalid input. try again.");
            }
            if (amount % 5 != 0) {
                throw new TopUpException("You can only TopUp in precise multiples of $5.00");
            } else if (amount + balance > 100) {
                throw new OverAmountException("Your credit can not over $100");
            } else {
//                Calendar date = Calendar.getInstance();
//                MyTi.UsersData.users.get(id).topUp(amount);
//                MyTi.UsersData.users.get(id).getTopUpHistories().add(new MyTi.TopUpHistory(balance, date));//add TopUp history

//      TopUp method used to use method in the comment
                UsersData.topUp(id, amount); //topUp
                System.out.println("your current balance is: " + UsersData.checkUserID(id) + "$");
                printBlackLine();
            }
        } catch (TopUpException err) { // others Exceptions
            System.err.println(err);
            System.err.println("Please try again");
            confirmMyTiTopUp(id, balance);
        } catch (OverAmountException errs) {
            System.err.println(errs);
            System.err.println("Please try again");
            confirmMyTiTopUp(id, balance);
        } catch (Exception e) {
            System.err.println("Invalid input, Try again");
            confirmMyTiTopUp(id, balance);
        }
    }
    //*************************** end charge MyTi *********************

    //*********************  Show credit *******************
    private void showCreditMenu() { // credit
        String userID;
        System.out.println("Which card ID: ");
        userID = new Scanner(System.in).nextLine();
        double balance = UsersData.checkUserID(userID);
        if (balance != -1) {
//            System.out.println();
            System.out.println("Your balance is: " + balance);
            printBlackLine();
        } else {
            System.err.println("User ID not found, try again");
            menuRun();
        }
    }

    //********************************   this area for take a journey ***********************
    private int checkValidTicket(String id) {  //this method is to check if the ticket is valid;
        int valid = 3;
        try {
            Calendar now = Calendar.getInstance();
            Calendar ticketTime = UsersData.users.get(id).getHistory().get(UsersData.users.get(id).historySize() - 1).getCalendar();
            int ticketType = UsersData.users.get(id).getHistory().get(UsersData.users.get(id).historySize() - 1).getTicketType();
//            int ticketZone = MyTi.UsersData.users.get(id).getHistory().get(MyTi.UsersData.users.get(id).historySize() - 1).getZone();
//            int thisZone = MyTi.UsersData.station.get(thisStop).getZone();
            //ticketTime is the most resent ticket time.
            int nowDate = now.get(Calendar.DAY_OF_YEAR);
            int ticketDate = ticketTime.get(Calendar.DAY_OF_YEAR);
            long nowSecend = now.getTimeInMillis();
            long ticketSecend = ticketTime.getTimeInMillis();
            if ((nowDate == ticketDate && nowSecend - ticketSecend <= 7200000) || (nowDate == ticketDate && ticketType == 2) || (nowDate == ticketDate && ticketType == 4)) {
                //valid;
                valid = 1;
            } else if (nowDate == ticketDate && nowSecend - ticketSecend > 7200000) {
                //buy a one day pass;
                valid = 2;
            } else if (nowDate != ticketDate) {
                //buy a new pass;
                valid = 3;
            }
        } catch (Exception e) {
            valid = 3;
        }
        return valid;
    }

    private boolean checkZoneValid(String id) {
        boolean valid = false;
        try {
            System.out.println("Start MyTi.Station:");
            String StartStation = new Scanner(System.in).nextLine();
            System.out.println("Destination MyTi.Station:");
            String Destination = new Scanner(System.in).nextLine();
            int startZone = UsersData.station.get(StartStation).getZone();
            int DestinationZone = UsersData.station.get(Destination).getZone();
            int thisZone = max(startZone, DestinationZone);//this may check when costumer swipe in destination station
            int ticketType = UsersData.users.get(id).getHistory().get(UsersData.users.get(id).historySize() - 1).getTicketType();
            if (ticketType == 1 && thisZone == 2) {
                valid = false;
            }
            if (ticketType == 1 && thisZone == 1) {
                valid = true;
            }
            if (ticketType == 2 && thisZone == 2) {
                valid = false;
            }
            if (ticketType == 2 && thisZone == 1) {
                valid = true;
            }
            if (ticketType == 3 && thisZone == 2) {
                valid = true;
            }
            if (ticketType == 3 && thisZone == 1) {
                valid = true;
            }
            if (ticketType == 4 && thisZone == 2) {
                valid = true;
            }
            if (ticketType == 4 && thisZone == 1) {
                valid = true;
            }
        } catch (Exception e) {
            valid = false;
        }
        return valid;
    }

    private int max(int startZone, int destinationZone) {
        int max = 1;
        if (startZone > destinationZone) {
            max = startZone;
        }
        if (startZone < destinationZone) {
            max = destinationZone;
        }
        if (startZone == destinationZone) {
            max = startZone;
        }
        return max;
    }

    private void takeJourneyMenu() {
        System.out.println("Which card ID:");
        String id = new Scanner(System.in).nextLine();
        if (UsersData.checkUser(id)) {
            takeJourneyOption(id);
        } else {
            System.out.println("ID not found, Try again");
            takeJourneyMenu();
        }
    }

    private void takeJourneyOption(String id) {
        try {
            journeyMenu();
            int m = new Scanner(System.in).nextInt();
            switch (m) {
                case 1:
                    systemTimeCkeck(id);
                    break;
                case 2:
                    inputTimeCheck(id);
                    break;
                case 0:
                    menuRun();
                    break;
                default:
                    System.out.println("Invalid Input. Try again");
                    takeJourneyOption(id);
            }
        } catch (Exception e) {
            System.out.println("Invalid Input. Try again");
            takeJourneyOption(id);
        }
    }

    private void systemTimeCkeck(String id) {
        int ticketValid = checkValidTicket(id);
        boolean zoneValid = checkZoneValid(id);
        if (!zoneValid) {
            System.err.println("You don't have valid ticket.");
            System.out.println("Ticket Zone not fit");
            menuRun();
        } else if (ticketValid == 3) {
            System.err.println("You don't have valid ticket.");
            System.out.println("Ticket Date not fit");
            menuRun();
        } else if (ticketValid == 2) {
            System.out.println("You don't have valid ticket.");
            System.out.println("You need Purchase new Travel Pass");
            menuRun();
        } else {
            System.out.println("Ticket Valid");
            menuRun();
        }
    }

    private void inputTimeCheck(String id) {
        int ticketValid = inputValidCheck(id);
        boolean zoneValid = checkZoneValid(id);
        if (!zoneValid) {
            System.err.println("You don't have valid ticket.");
            System.out.println("Ticket Zone not fit");
            menuRun();
        } else if (ticketValid == 3) {
            System.err.println("You don't have valid ticket.");
            System.out.println("Ticket Date not fit");
            menuRun();
        } else if (ticketValid == 2) {
            System.out.println("You don't have valid ticket.");
            System.out.println("You need Purchase new Travel Pass");
            menuRun();
        } else {
            System.out.println("Ticket Valid");
            menuRun();
        }

    }

    private int inputValidCheck(String id) {
        int valid = 3;
        Calendar date = Calendar.getInstance();
        try {
//            System.out.println("Enter the day:(day of the month)");
//            int day = new Scanner(System.in).nextInt();
            int d;
            do {
                System.out.println("Enter the day:(Day of the Week");
                String day = new Scanner(System.in).nextLine();
                switch (day) {
                    case "mon":
                        d = Calendar.MONDAY;
                        break;
                    case "tue":
                        d = Calendar.TUESDAY;
                        break;
                    case "wed":
                        d = Calendar.WEDNESDAY;
                        break;
                    case "thu":
                        d = Calendar.THURSDAY;
                        break;
                    case "fri":
                        d = Calendar.FRIDAY;
                        break;
                    case "sat":
                        d = Calendar.SATURDAY;
                        break;
                    case "sun":
                        d = Calendar.SUNDAY;
                        break;
                    default:
                        d = 0;
                }
            } while (d != 0);
            printBlackLine();
            System.out.println("Enter the TIME:(HHMM)");
            int time = new Scanner(System.in).nextInt();
            int hour = time / 100;
            int min = time % 100;
            if (min / 60 >= 1 || hour / 24 >= 1) {
                throw new Exception("Invalid Time input. Try again.");
            }
//            date.set(Calendar.DAY_OF_MONTH, day);
            date.set(Calendar.DAY_OF_WEEK, d);
            date.set(Calendar.HOUR_OF_DAY, hour);
            date.set(Calendar.MINUTE, min);
        } catch (Exception e) {
            System.err.println(e);
            inputValidCheck(id);
        }
        try {
            Calendar ticketTime = UsersData.users.get(id).getHistory().get(UsersData.users.get(id).historySize() - 1).getCalendar();
            int ticketType = UsersData.users.get(id).getHistory().get(UsersData.users.get(id).historySize() - 1).getTicketType();
//            int ticketZone = MyTi.UsersData.users.get(id).getHistory().get(MyTi.UsersData.users.get(id).historySize() - 1).getZone();
//            int thisZone = MyTi.UsersData.station.get(thisStop).getZone();
            //ticketTime is the most resent ticket time.
            int nowDate = date.get(Calendar.DAY_OF_YEAR);
            int ticketDate = ticketTime.get(Calendar.DAY_OF_YEAR);
            long nowSecend = date.getTimeInMillis();
            long ticketSecend = ticketTime.getTimeInMillis();
            if ((nowDate == ticketDate && nowSecend - ticketSecend <= 7200000) || (nowDate == ticketDate && ticketType == 2) || (nowDate == ticketDate && ticketType == 4)) {
                //valid;
                valid = 1;
            } else if (nowDate == ticketDate && nowSecend - ticketSecend > 7200000) {
                //buy a one day pass;
                valid = 2;
            } else if (nowDate != ticketDate) {
                //buy a new pass;
                valid = 3;
            }
        } catch (Exception e) {
            valid = 3;
        }
        return valid;
    }

    public static double getPrice(int type){
        if(type ==1) {
            return zoneOneTwoHoursPassPrice;
        }
        else if(type ==2){
            return zoneOneOneDayPassPrice;
        }else if(type ==3){
            return zoneTwoTwoHoursPassPrice;
        }else {
            return zoneTwoOneDayPassPrice;
        }
    }


    private void printBlackLine() { // this method just for print a empty line. just for good looking ......
        System.out.println(" ");
    }
}

