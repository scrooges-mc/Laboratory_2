import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class exercise2Test {

    @Test
    void testJobPayment() {
        Job job = new Job(1000, 2);
        assertEquals(2000, job.payment(), "Ошибка в расчёте оплаты труда в Job");
    }

    @Test
    void testBadWorkPaymentWithLowBad() {
        BadWork job = new BadWork(1000, 2, 0.5);
        assertEquals(2000, job.payment(), "Ошибка в BadWork при низком коэффициенте bad");
    }

    @Test
    void testBadWorkPaymentWithHighBad() {
        BadWork job = new BadWork(1000, 2, 1);
        assertEquals(4000, job.payment(), "Ошибка в BadWork при коэффициенте bad >= 1");
    }

    @Test
    void testShiftworkTotalPayments() {
        Shiftwork shiftwork = new Shiftwork("Shiftwork", 2000, 1.5, 3000, 2);
        assertEquals(9000, shiftwork.calculateTotalPayments(), "Ошибка в расчёте зарплаты Shiftwork");
    }

    @Test
    void testCommonWorkTotalPayments() {
        CommonWork commonWork = new CommonWork("Common", 2000, 1.5, 3000, 2, 1, 0.5);
        assertEquals(12000, commonWork.calculateTotalPayments(), "Ошибка в расчёте зарплаты CommonWork");
    }

    @Test
    void testCombineTotalPayments() {
        Combine combine = new Combine("Combine", 2000, 1.5, 3000, 2, 1);
        assertEquals(15000, combine.calculateTotalPayments(), "Ошибка в расчёте зарплаты Combine");
    }

    @Test
    void testZeroPaymentJob() {
        Job job = new Job(0, 2);
        assertEquals(0, job.payment(), "Ошибка в Job при нулевой ставке");
    }

    @Test
    void testZeroPaymentBadWork() {
        BadWork job = new BadWork(0, 2, 1);
        assertEquals(0, job.payment(), "Ошибка в BadWork при нулевой ставке");
    }

    @Test
    void testNegativeValuesJob() {
        Job job = new Job(-1000, 2);
        assertEquals(-2000, job.payment(), "Ошибка в Job при отрицательных значениях");
    }

    @Test
    void testNegativeValuesBadWork() {
        BadWork job = new BadWork(-1000, 2, 1);
        assertEquals(-4000, job.payment(), "Ошибка в BadWork при отрицательных значениях");
    }
}
