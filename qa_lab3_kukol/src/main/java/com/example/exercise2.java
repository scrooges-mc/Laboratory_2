package com.example;

/**
 * Класс Job представляет работу с фиксированной ставкой и коэффициентом.
 *
 * Формула расчета:
 * <pre>{@code
 * Оплата = ставка * коэффициент
 * }</pre>
 */
class Job {
    protected double payday;
    protected double k;

    /**
     * Конструктор по умолчанию. Устанавливает значения по нулям.
     */
    public Job() {
        payday = 0;
        k = 0;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param p  ставка
     * @param kk коэффициент
     */
    public Job(double p, double kk) {
        payday = p;
        k = kk;
    }

    /**
     * Метод расчета оплаты.
     *
     * @return результат умножения ставки на коэффициент
     */
    public double payment() {
        return payday * k;
    }
}

/**
 * Класс BadWork расширяет Job, добавляя негативный коэффициент bad.
 * Если bad >= 1, итоговая сумма удваивается.
 */
class BadWork extends Job {
    private double bad = 0.1;

    /**
     * Конструктор по умолчанию.
     */
    public BadWork() {
        payday = 0;
        k = 0;
        bad = 0;
    }

    /**
     * Конструктор с параметрами.
     *
     * @param p   ставка
     * @param kk  коэффициент
     * @param bad негативный фактор
     */
    public BadWork(double p, double kk, double bad) {
        super(p, kk);
        this.bad = bad;
    }

    /**
     * Расчет оплаты с учетом bad-фактора.
     *
     * @return оплата, возможно удвоенная
     */
    @Override
    public double payment() {
        double q = payday * k;
        if (bad >= 1) {
            q *= 2;
        }
        return q;
    }
}

/**
 * Абстрактный класс Employer — описывает работодателя.
 */
abstract class Employer {
    protected String fam;

    /**
     * Конструктор.
     *
     * @param f название типа работодателя
     */
    public Employer(String f) {
        fam = f;
    }

    /**
     * Абстрактный метод расчета всех выплат.
     *
     * @return итоговая сумма выплат
     */
    public abstract long calculateTotalPayments();
}

/**
 * Shiftwork — работа по сменам. Содержит две обычные работы.
 */
class Shiftwork extends Employer {
    private Job job1;
    private Job job2;

    /**
     * @param f   тип работодателя
     * @param p1  ставка первой работы
     * @param kk1 коэффициент первой работы
     * @param p2  ставка второй работы
     * @param kk2 коэффициент второй работы
     */
    public Shiftwork(String f, double p1, double kk1, double p2, double kk2) {
        super(f);
        job1 = new Job(p1, kk1);
        job2 = new Job(p2, kk2);
    }

    /**
     * Расчет оплаты по двум обычным работам.
     *
     * @return округлённая сумма
     */
    @Override
    public long calculateTotalPayments() {
        return Math.round(job1.payment() + job2.payment());
    }
}

/**
 * CommonWork — обычная работа с учетом негативных факторов.
 */
class CommonWork extends Employer {
    private BadWork job1;
    private BadWork job2;

    /**
     * @param f   тип
     * @param p1  ставка 1
     * @param kk1 коэффициент 1
     * @param p2  ставка 2
     * @param kk2 коэффициент 2
     * @param b1  bad-фактор 1
     * @param b2  bad-фактор 2
     */
    public CommonWork(String f, double p1, double kk1, double p2, double kk2, double b1, double b2) {
        super(f);
        this.job1 = new BadWork(p1, kk1, b1);
        this.job2 = new BadWork(p2, kk2, b2);
    }

    /**
     * Расчет оплаты с учетом bad-факторов.
     *
     * @return округлённая сумма
     */
    @Override
    public long calculateTotalPayments() {
        return Math.round(job1.payment() + job2.payment());
    }
}

/**
 * Combine — комбинированный тип: обычная и "плохая" работа.
 */
class Combine extends Employer {
    private Job job1;
    private BadWork job2;

    /**
     * @param f   тип
     * @param p1  ставка обычной работы
     * @param kk1 коэффициент обычной работы
     * @param p2  ставка bad-работы
     * @param kk2 коэффициент bad-работы
     * @param b2  bad-фактор
     */
    public Combine(String f, double p1, double kk1, double p2, double kk2, double b2) {
        super(f);
        this.job1 = new Job(p1, kk1);
        this.job2 = new BadWork(p2, kk2, b2);
    }

    /**
     * Расчет комбинированной оплаты.
     *
     * @return округлённая сумма
     */
    @Override
    public long calculateTotalPayments() {
        return Math.round(job1.payment() + job2.payment());
    }
}

/**
 * Основной класс приложения.
 *
 * <p>Формула оплаты:
 * <pre>{@code
 * payment = ставка * коэффициент
 * }</pre>
 *
 * <p>Пример заработной платы:
 * <img src="C:\Users\New\Java_project\qa_lab3_kukol\docs\images\plata.png" width="700"/>
 *
 * <p>Граф зависимостей классов:
 * <img src="C:\Users\New\Java_project\qa_lab3_kukol\docs\images\graphviz.png" width="500"/>
 */
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