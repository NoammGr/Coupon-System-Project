package DailyJob;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;

import Core.Coupon;
import Core.CouponSystemException;
import DAO.CouponsDAO;
import DAO.CouponsDBDAO;

/**
 * This thread will check every day if there is any coupons expired and will
 * delete them .
 */
public class CouponExpirationDailyJob implements Runnable {

    // private Thread thread = new Thread(this, "CouponExpirationDailyJob");

    private static CouponsDAO couponsDAO = new CouponsDBDAO();
    private boolean quit = true;

    @Override
    public void run() {
        java.sql.Date date = new java.sql.Date(Calendar.getInstance().getTime().getTime());
        Collection<Coupon> coupons = new ArrayList<Coupon>();
        while (quit) {
            try {
                coupons.addAll(couponsDAO.getAllCoupon());
                for (Coupon coupon : coupons) {
                    if (coupon.getEndDate().compareTo(date) < 0) {
                        couponsDAO.deleteCoupon(coupon.getId());
                    }
                }
                Thread.sleep(43_200); // 12 hours
            } catch (InterruptedException e) {
                System.out.println("Thread was interupted");
                quit = false;
            } catch (CouponSystemException e) {
                System.out.println("Thread error , please check thread again !");
                quit = false;
            }

        }
    }

    // public void startJob() {
    // this.thread.start();
    // }

    // public void stop() {
    // this.thread.interrupt();
    // }
}
