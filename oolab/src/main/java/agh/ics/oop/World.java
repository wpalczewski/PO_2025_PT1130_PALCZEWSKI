package agh.ics.oop;






public class World {
    public static void main(String[] args) {
        System.out.println("System wystartował");


        MoveDirection[] directions = OptionsParser.parse(args);
        for (int index = 0; index < args.length; index++) {
            System.out.print(args[index]);
            if (index != args.length - 1) {
                System.out.println(",");
            }
        }
        System.out.println("");
        System.out.println("System zakończył działanie");


    }
    public static void run(String[] args){
        String message = switch (arg){
            case "f" -> ("zwierzak idzie do przodu");
            case "b" -> ("zwierzak idzie do tyłu");
            case "r" -> ("zwierzak skręca w prawo");
            case "l" -> ("zwierzak skręca w lewo");
            default -> ("Nieznana komenda");
        };
        System.out.println(message);




    }




    public enum MoveDirection {
        FORWARD,
        BACKWARD,
        RIGHT,
        LEFT
    }




}
