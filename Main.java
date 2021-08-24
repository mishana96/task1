import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        final double FUEL_PRICE100 = 46.10;
        final double FUEL_PRICE300 = 47.50;
        final double FUEL_PRICE200400 = 48.90;
        final double FUEL_WASTE_100 = 12.5/100;
        final double FUEL_WASTE_200 = 12.0/100;
        final double FUEL_WASTE_300 = 11.5/100;
        final double FUEL_WASTE_400 = 20.0/100;
        System.out.print("Введите входные данные через запятую: ");
        int waste_100 = 0,  waste_200= 0, waste_300= 0, waste_400= 0;
        Scanner scanner = new Scanner(System.in);
        String dataIn = scanner.nextLine();
        String[] dataArr = dataIn.split(", ");//C100_1-100, C200_1-120-1200, C300_1-120-30, C400_1-80-20, C100_2-50, C200_2-40-1000, C300_2-200-45, C400_2-10-20, C100_3-10, C200_3-170-1100, C300_3-150-29, C400_3-100-28, C100_1-300, C200_1-100-750, C300_1-32-15
        for(String i : dataArr) {
            String[] carArr = i.split("-");
            switch (carArr[0].substring(0, 4)) {
                case ("C100"):
                    waste_100 += Integer.parseInt(carArr[1]);
                    break;
                case ("C200"):
                    waste_200 += Integer.parseInt(carArr[1]);
                    break;
                case ("C300"):
                    waste_300 += Integer.parseInt(carArr[1]);
                    break;
                case ("C400"):
                    waste_400 += Integer.parseInt(carArr[1]);
                    break;
            }
        }
        System.out.println("waste_full: " + (waste_100*FUEL_WASTE_100*FUEL_PRICE100+waste_100*FUEL_WASTE_100*FUEL_PRICE100+waste_300*FUEL_WASTE_300*FUEL_PRICE300+waste_400*FUEL_WASTE_400*FUEL_PRICE200400));
        System.out.println("waste_100: " + waste_100*FUEL_WASTE_100*FUEL_PRICE100);
        System.out.println("waste_200: " + waste_200*FUEL_WASTE_200*FUEL_PRICE200400);
        System.out.println("waste_300: " + waste_300*FUEL_WASTE_300*FUEL_PRICE300);
        System.out.println("waste_400: " + waste_400*FUEL_WASTE_400*FUEL_PRICE200400);
        System.out.println("waste_MAX: " + Math.max(waste_100*FUEL_WASTE_100*FUEL_PRICE100,Math.max(waste_100*FUEL_WASTE_100*FUEL_PRICE100,Math.max(waste_300*FUEL_WASTE_300*FUEL_PRICE300,waste_400*FUEL_WASTE_400*FUEL_PRICE200400))));
        System.out.println("waste_MIN: " + Math.min(waste_100*FUEL_WASTE_100*FUEL_PRICE100,Math.min(waste_100*FUEL_WASTE_100*FUEL_PRICE100,Math.min(waste_300*FUEL_WASTE_300*FUEL_PRICE300,waste_400*FUEL_WASTE_400*FUEL_PRICE200400))));
        System.out.println("Выберите тип авто: C100, C200, C300, C400 ");
        String typeCar = scanner.nextLine();
        int parameterSort;
        if (typeCar.equals("C100")){
            parameterSort = 1;}
        else{
        System.out.println("Выберите параметр сортировки, по пробегу - 1, по доп.параметру - 2");
        parameterSort = scanner.nextInt();}
        System.out.println("Выберите тип сортировки, по возрастанию - 1, по убыванию - 2");
        int typeSort = scanner.nextInt();
        Sort_Car(dataArr, typeCar, typeSort, parameterSort);
    }
    public static void Sort_Car(String[] carsArr, String typeCar, int typeSort, int parameterSort){
        int countCars =0;
        String[] arr;
        if (typeCar.equals("C100")) {
            int[][] result = new int[carsArr.length][2];
            for (String s : carsArr) {
                if (s.substring(0, 4).equals(typeCar)) {
                    arr = s.substring(5).split("-");
                    result[countCars][0] = Integer.parseInt(arr[0]);
                    result[countCars][1] = Integer.parseInt(arr[1]);
                    countCars++;
                }
            }
            for (int i = 0; i < countCars - 1; i++) {
                for (int k = i + 1; k < countCars; k++) {
                    if (result[i][0] == result[k][0]) {
                        result[i][1] += result[k][1];
                        result[k][0] = 0;
                        result[k][1] = 0;
                    }
                }
            }


            boolean isSorted = false;
            int[] buf;
            while(!isSorted) {
                isSorted = true;
                for (int i = 0; i < result.length-1; i++) {
                    if(result[i][parameterSort] > result[i+1][parameterSort]){
                        isSorted = false;

                        buf = result[i];
                        result[i] = result[i+1];
                        result[i+1] = buf;
                    }
                }
            }
            int j;
            if (typeSort == 2){
                j = result.length-1;
                while (result[j][0] != 0){
                    System.out.print(result[j][0] + " ");
                    System.out.println(result[j][1]);
                    j--;
                }}
            else{
                j = 0;
                while (result[j][0] == 0){j++;}
                for(int i = j; i< result.length; i++){
                    System.out.print(result[i][0] + " ");
                    System.out.println(result[i][1]);
                }}
            }
        else{
            int[][] result = new int[carsArr.length][3];
            for (String s : carsArr) {
                if (s.substring(0, 4).equals(typeCar)) {
                    arr = s.substring(5).split("-");
                    result[countCars][0] = Integer.parseInt(arr[0]);
                    result[countCars][1] = Integer.parseInt(arr[1]);
                    result[countCars][2] = Integer.parseInt(arr[2]);
                    countCars++;
                }
            }
            for (int i = 0; i < countCars - 1; i++) {
                for (int k = i + 1; k < countCars; k++) {
                    if (result[i][0] == result[k][0]) {
                        result[i][1] += result[k][1];
                        result[i][2] += result[k][2];
                        result[k][0] = 0;
                        result[k][1] = 0;
                        result[k][2] = 0;
                    }
                }
            }
            boolean isSorted = false;
            int[] buf;
            while(!isSorted) {
                isSorted = true;
                for (int i = 0; i < result.length-1; i++) {
                    if(result[i][parameterSort] > result[i+1][parameterSort]){
                        isSorted = false;

                        buf = result[i];
                        result[i] = result[i+1];
                        result[i+1] = buf;
                    }
                }
            }
            int j;
            if (typeSort == 2){
            j = result.length-1;
            while (result[j][0] != 0){
                System.out.print(result[j][0] + " ");
                System.out.print(result[j][1] + " ");
                System.out.println(result[j][2]);
                j--;
            }}
            else{
                j = 0;
                while (result[j][0] == 0){j++;}
                for(int i = j; i< result.length; i++){
                    System.out.print(result[i][0] + " ");
                    System.out.print(result[i][1] + " ");
                    System.out.println(result[i][2]);
                }
            }
            }
        }

    }

