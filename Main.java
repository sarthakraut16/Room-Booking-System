import java.util.*;

class Room {
    private int roomNumber;
    private String guestName;

    public Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.guestName = null;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getGuestName() {
        return guestName;
    }

    public boolean isBooked() {
        return guestName != null;
    }

    public void bookRoom(String guestName) {
        this.guestName = guestName;
    }

    public void cancelRoom() {
        this.guestName = null;
    }

    public void displayRoomInfo() {
        System.out.println("Room Number: " + roomNumber);
        if (isBooked()) {
            System.out.println("Guest Name: " + guestName);
        } else {
            System.out.println("Room is available.");
        }
    }
}

interface HotelManagement {
    void bookRoom(int roomNumber, String guestName);

    void cancelRoom(int roomNumber);

    void showBookedRooms();
}

class Hotel implements HotelManagement {
    private Room[] rooms;

    public Hotel(int totalRooms) {
        rooms = new Room[totalRooms];
        for (int i = 0; i < totalRooms; i++) {
            rooms[i] = new Room(i + 1);
        }
    }

    public void bookRoom(int roomNumber, String guestName) {
        if (roomNumber <= 0 || roomNumber > rooms.length) {
            System.out.println("Invalid room number.");
            return;
        }
        Room room = rooms[roomNumber - 1];
        if (room.isBooked()) {
            System.out.println("Room " + roomNumber + " is already booked.");
        } else {
            room.bookRoom(guestName);
            System.out.println("Room " + roomNumber + " booked by " + guestName + ".");
        }
    }

    public void cancelRoom(int roomNumber) {
        if (roomNumber <= 0 || roomNumber > rooms.length) {
            System.out.println("Invalid room number.");
            return;
        }
        Room room = rooms[roomNumber - 1];
        if (room.isBooked()) {
            room.cancelRoom();
            System.out.println("Room " + roomNumber + " booking has been canceled.");
        } else {
            System.out.println("Room " + roomNumber + " is not booked.");
        }
    }

    public void showBookedRooms() {
        boolean found = false;
        for (Room room : rooms) {
            if (room.isBooked()) {
                room.displayRoomInfo();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No rooms are booked.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Hotel hotel = new Hotel(10);

        System.out.print("Enter Your Name:");
        Scanner s = new Scanner(System.in);
        String guestName = s.nextLine();

        System.out.print("Enter Room Number:");
        Scanner sc = new Scanner(System.in);
        int roomNo = sc.nextInt();

        hotel.bookRoom(roomNo, guestName);

        System.out.println("\nList of booked rooms:");
        hotel.showBookedRooms();

        System.out.print("\ndo you want to cancle the Booking (Y/N): ");
        Scanner sc1 = new Scanner(System.in);
        char c = sc1.next().charAt(0);

        if (c == 'y' || c == 'Y') {
            System.out.print("\nEnter the room no. that you want to cancle: ");
            Scanner a = new Scanner(System.in);
            int cancleRoomNo = a.nextInt();
            hotel.cancelRoom(cancleRoomNo);

            System.out.println("\nList of booked rooms after cancellation:");
            hotel.showBookedRooms();
        } else {
            System.out.println("Thank you for using our service");
        }

    }
}
