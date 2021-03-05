import java.io.*;
import java.util.Locale;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(new Locale("Russian"));
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        boolean answerGiven = false;
        boolean scannerLine = true;
        while (!answerGiven) {
            System.out.print("Ввод из файла/из строки (true/false): ");
            try {
                boolean method = Boolean.parseBoolean(scanner.nextLine());
                if (method){
                    System.out.print("Введите название файла: ");
                    String path = scanner.nextLine();
                    bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(path))));
                    scannerLine = false;
                }
                answerGiven = true;
            } catch (Exception e) {}
        }

        double x3, x2, x1, k, a, b, eps;
        Func func = new Func();

        if (scannerLine) {
            System.out.print("Коэффициент перед x^3: ");
            x3 = scanner.nextDouble();
            System.out.print("Коэффициент перед x^2: ");
            x2 = scanner.nextDouble();
            System.out.print("Коэффициент перед x: ");
            x1 = scanner.nextDouble();
            System.out.print("Свободный член: ");
            k = scanner.nextDouble();
            System.out.print("Левая граница приближения: ");
            a = scanner.nextDouble();
            System.out.print("Правая граница приближения: ");
            b = scanner.nextDouble();
            System.out.print("Погрешность: ");
            eps = scanner.nextDouble();
            func = new Func(x3, x2, x1, k, a, b, eps);
        }
        else {
            try {
                //Ввод коэффициентов и свободного члена
                StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                x3 = Double.parseDouble(stringTokenizer.nextToken());
                x2 = Double.parseDouble(stringTokenizer.nextToken());
                x1 = Double.parseDouble(stringTokenizer.nextToken());
                k = Double.parseDouble(stringTokenizer.nextToken());

                //Ввод начального приближения
                stringTokenizer = new StringTokenizer(bufferedReader.readLine());
                a = Double.parseDouble(stringTokenizer.nextToken());
                b = Double.parseDouble(stringTokenizer.nextToken());
                eps = Double.parseDouble(bufferedReader.readLine());
                func = new Func(x3, x2, x1, k, a, b, eps);
            } catch (Exception e){
                e.printStackTrace();
            }
        }

        System.out.print("Выберите метод: \n" +
                "1. Половинного деления \n" +
                "2. Метод Ньютона \n" +
                "3. Метод простой итерации \n" +
                "Ваш ответ: ");
        String answer = "";
        answerGiven = false;
        while (!answerGiven) {
            switch (scanner.nextInt()) {
                case (1):
                    answer = func.func1();
                    answerGiven = true;
                    break;
                case (2):
                    answer = func.func3();
                    answerGiven = true;
                    break;
                case (3):
                    answer = func.func5();
                    answerGiven = true;
                    break;
                default:
                    System.err.println("Ошибка: не тот номер \n" +
                            "попробуйте еще раз");
            }
        }

        answerGiven = false;
        while (!answerGiven) {
            System.out.print("Вывод в файл/строку (true/false): ");
            scanner.nextLine();
            try {
                boolean method = Boolean.parseBoolean(scanner.nextLine());
                if (method){
                    System.out.print("Введите название файла: ");
                    String path = scanner.nextLine();
                    try(FileOutputStream fos=new FileOutputStream(path)) {
                        byte[] buffer = answer.getBytes();
                        fos.write(buffer, 0, buffer.length);
                    }
                    catch(IOException ex){
                        System.out.println(ex.getMessage());
                    }
                }
                else System.out.println(answer);
                answerGiven = true;
            } catch (Exception e) {}
        }
    }
}
