// завдання 1

import java.util.Scanner;

public class Money {
    private long grn;
    private byte kop;

    public Money(long grn, byte kop) {
        this.grn = grn;
        this.kop = kop;
    }



    public int toInt() {
        return (int) (this.grn * 100 + this.kop);
    }


    public long getGrn() {
        return grn;
    }

    public void setGrn(long grn) {
        this.grn = grn;
    }

    public byte getKop() {
        return kop;
    }

    public void setKop(byte kop) {
        this.kop = kop;
    }

    public Money add(Money money) {
        long newGrn = this.grn + money.getGrn();
        byte newKop = (byte) (this.kop + money.getKop());
        if (newKop >= 100) {
            newGrn += newKop / 100;
            newKop %= 100;
        }
        return new Money(newGrn, newKop);
    }

    public Money subtract(Money money) {
        long newGrn = this.grn - money.getGrn();
        byte newKop = (byte) (this.kop - money.getKop());
        if (newKop < 0) {
            newGrn -= 1;
            newKop += 100;
        }
        return new Money(newGrn, newKop);
    }


    public void divide(int divider) {
        if (divider != 0) {
            long totalKop = this.grn * 100 + this.kop;
            totalKop /= divider;
            this.grn = totalKop / 100;
            this.kop = (byte) (totalKop % 100);
        }
    }

    public void divideByMoney(Money money) {
        long totalCents1 = this.grn * 100 + this.kop;
        long totalCents2 = money.getGrn() * 100 + money.getKop();
        if (totalCents2 != 0) {
            totalCents1 /= totalCents2;
            this.grn = totalCents1 / 100;
            this.kop = (byte) (totalCents1 % 100);
        }
    }

    public boolean equals(Money money) {
        return this.grn == money.getGrn() && this.kop == money.getKop();
    }

    public int compareTo(Money money) {
        if (this.grn > money.getGrn()) {
            return 1;
        } else if (this.grn < money.getGrn()) {
            return -1;
        } else return Byte.compare(this.kop, money.getKop());
    }

    @Override
    public String toString() {
        return String.format("%d.%02d", this.grn, this.kop);
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть першу суму (грн): ");
        long grn1 = scanner.nextLong();
        System.out.print("Введіть першу суму (коп): ");
        byte kop1 = scanner.nextByte();
        Money money1 = new Money(grn1, kop1);

        System.out.print("Введіть другу суму (грн): ");
        long grn2 = scanner.nextLong();
        System.out.print("Введіть другу суму (коп): ");
        byte kop2 = scanner.nextByte();
        Money money2 = new Money(grn2, kop2);

        System.out.println("Перша сума: " + money1.toInt());
        System.out.println("Друга сума: " + money2.toInt());

        System.out.println("Сума двох сум: " + money1.add(money2).toInt());
        System.out.println("Різниця двох сум: " + money1.subtract(money2).toInt());

        System.out.print("Введіть дільник для першої суми: ");
        int divider = scanner.nextInt();
        money1.divide(divider);
        System.out.println("Сума першої суми після ділення: " + money1);
        System.out.println("Порівняння двох сум: " + money1.compareTo(money2));
    }
}


// завдання 2

import java.util.Scanner;

public class Vector {
    private final double x;
    private final double y;
    private final double z;


    public Vector(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    public double getMagnitude() {
        return Math.sqrt(x*x + y*y + z*z);
    }


    public Vector multiply(double scalar) {
        return new Vector(x*scalar, y*scalar, z*scalar);
    }


    public Vector add(Vector other) {
        return new Vector(x+other.x, y+other.y, z+other.z);
    }


    public double dotProduct(Vector other) {
        return x*other.x + y*other.y + z*other.z;
    }


    public Vector crossProduct(Vector other) {
        double newX = y*other.z - z*other.y;
        double newY = z*other.x - x*other.z;
        double newZ = x*other.y - y*other.x;
        return new Vector(newX, newY, newZ);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введіть координати першого вектора:");
        double x1 = scanner.nextDouble();
        double y1 = scanner.nextDouble();
        double z1 = scanner.nextDouble();
        Vector v1 = new Vector(x1, y1, z1);

        System.out.println("Введіть координати другого вектора:");
        double x2 = scanner.nextDouble();
        double y2 = scanner.nextDouble();
        double z2 = scanner.nextDouble();
        Vector v2 = new Vector(x2, y2, z2);

        System.out.println("Модуль вектора v1: " + v1.getMagnitude());
        Vector v3 = v1.multiply(2);
        System.out.println("Вектор v3: (" + v3.x + ", " + v3.y + ", " + v3.z + ")");
        Vector v4 = v1.add(v2);
        System.out.println("Вектор v4: (" + v4.x + ", " + v4.y + ", " + v4.z + ")");
        double dotProduct = v1.dotProduct(v2);
        System.out.println("Скалярний добуток векторів v1 та v2: " + dotProduct);
        Vector crossProduct = v1.crossProduct(v2);
        System.out.println("Векторний добуток векторів v1 та v2: (" + crossProduct.x + ", " + crossProduct.y + ", " + crossProduct.z + ")");
    }
}

// завдання 3
import java.math.BigInteger;
import java.util.Scanner;

public class VeryLongNumber {
    private final BigInteger number;

    public VeryLongNumber(String number) {
        this.number = new BigInteger(number);
    }

    public VeryLongNumber add(VeryLongNumber other) {
        BigInteger result = this.number.add(other.number);
        return new VeryLongNumber(result.toString());
    }

    public VeryLongNumber subtract(VeryLongNumber other) {
        BigInteger result = this.number.subtract(other.number);
        return new VeryLongNumber(result.toString());
    }

    public VeryLongNumber multiply(VeryLongNumber other) {
        BigInteger result = this.number.multiply(other.number);
        return new VeryLongNumber(result.toString());
    }

    public VeryLongNumber divide(VeryLongNumber other) {
        BigInteger result = this.number.divide(other.number);
        return new VeryLongNumber(result.toString());
    }

    public VeryLongNumber pow(int exponent) {
        BigInteger result = this.number.pow(exponent);
        return new VeryLongNumber(result.toString());
    }

    @Override
    public String toString() {
        return number.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the first number: ");
        String number1 = scanner.nextLine();

        System.out.print("Enter the second number: ");
        String number2 = scanner.nextLine();

        VeryLongNumber n1 = new VeryLongNumber(number1);
        VeryLongNumber n2 = new VeryLongNumber(number2);

        System.out.println("Addition: " + n1.add(n2).toString());
        System.out.println("Subtraction: " + n1.subtract(n2).toString());
        System.out.println("Multiplication: " + n1.multiply(n2).toString());
        System.out.println("Division: " + n1.divide(n2).toString());
        System.out.println("Power: " + n1.pow(3).toString());

        scanner.close();
    }
}
