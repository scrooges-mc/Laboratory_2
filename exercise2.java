class Job {
    protected double payday;
    protected double k;

    public Job() {
        payday = 0;
        k = 0;
    }

    public Job(double p, double kk) {
        payday = p;
        k = kk;
    }

    public double payment() {
        return payday * k;
    }
}

class BadWork extends Job {
    private double bad = 0.1;

    public BadWork() {
        payday = 0;
        k = 0;
        bad = 0;
    }

    public BadWork(double p, double kk, double bad) {
        super(p, kk);
        this.bad = bad;
    }

    @Override
    public double payment() {
        double q = payday * k;
        if (bad >= 1) {
            q *= 2;
        }
        return q;
    }
}

abstract class Employer {
    protected String fam;

    public Employer(String f) {
        fam = f;
    }

    public abstract long calculateTotalPayments();
}

class Shiftwork extends Employer {
    private Job job1;
    private Job job2;

    public Shiftwork(String f, double p1, double kk1, double p2, double kk2) {
        super(f);
        job1 = new Job(p1, kk1);
        job2 = new Job(p2, kk2);
    }

    @Override
    public long calculateTotalPayments() {
        return Math.round(job1.payment() + job2.payment());
    }
}

class CommonWork extends Employer {
    private BadWork job1;
    private BadWork job2;

    public CommonWork(String f, double p1, double kk1, double p2, double kk2, double b1, double b2) {
        super(f);
        this.job1 = new BadWork(p1, kk1, b1);
        this.job2 = new BadWork(p2, kk2, b2);
    }

    @Override
    public long calculateTotalPayments() {
        return Math.round(job1.payment() + job2.payment());
    }
}

class Combine extends Employer {
    private Job job1;
    private BadWork job2;

    public Combine(String f, double p1, double kk1, double p2, double kk2, double b2) {
        super(f);
        this.job1 = new Job(p1, kk1);
        this.job2 = new BadWork(p2, kk2, b2);
    }

    @Override
    public long calculateTotalPayments() {
        return Math.round(job1.payment() + job2.payment());
    }
}
public class exercise2 {
    public static void main(String[] args) {
        double k, k1, k2;

        Shiftwork s = new Shiftwork("Вахтовый метод", 200000, 1.2, 250000, 1.1);
        k = s.calculateTotalPayments();
        System.out.println(k);

        CommonWork c = new CommonWork("Обычный", 200000, 1.2, 250000, 1.1, 1, 0.2);
        k1 = c.calculateTotalPayments();
        System.out.println(k1);

        Combine m = new Combine("Комбинированный", 200000, 1.2, 250000, 1.1, 0.2);
        k2 = m.calculateTotalPayments();
        System.out.println(k2);
    }
}
