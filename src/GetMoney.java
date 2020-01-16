import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @author xupangen on 2020/1/15.
 */

class GetMoney {


    StringBuilder stringBuilder;

    public GetMoney() {
        stringBuilder = new StringBuilder();
    }

    public static void main(String[] args) {
        BigDecimal a = new BigDecimal("300000000");
        BigDecimal b = new BigDecimal("0.0005");
//        BigDecimal a = new BigDecimal("450000000");
//        System.out.println("结果->" + a.multiply(b));

       /* String[] array = {"2018-09-24", "2019-03-29", "2019-04-08"
                , "2019-04-30", "2019-05-07", "2019-05-10", "2019-05-14"
                , "2019-05-23", "2019-05-29", "2019-07-02", "2019-07-09"};
        String[] money = {"1159589.04", "380231.32"
                , "1297079.98", "2459728.97", "2769728.94", "2771163.13"
                , "2241993.53", "2547517.96", "49270885.22", "19634712.28"
                };*/
        String[] array = {"2018-09-24", "2018-09-27", "2018-11-01"
                , "2019-03-28", "2019-04-02", "2019-06-28"};
        String[] money = {"10000000.00", "48480000.00"
                , "44398168.77", "1431952.46", "28061435.00"
        };
        try {
            new GetMoney().getMoney(array, money, a, b);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    /**
     * @param dateArray 日期
     * @param y         归还
     * @param z         本金
     */
    public String getMoney(String[] dateArray, String[] y, BigDecimal z, BigDecimal lilv) throws ParseException {
        // 违约金
        DecimalFormat df1 = new DecimalFormat("0.00");
        for (int i = 0; i < dateArray.length - 1; i++) {
            int days = daysBetween(dateArray[i], dateArray[i + 1]);
            BigDecimal ll = new BigDecimal(days* lilv.doubleValue());
            BigDecimal x = ll.multiply(z);
            System.out.println("违约金为" + df1.format(z) + "*" + lilv.toString() + "*" + days + "=" + df1.format(x) + "元\r\n"
                    + "本金" + df1.format(z) + "  归还" + y[i]);

            stringBuilder.append("违约金为" + df1.format(z) + "*" +  lilv.toString() +"*" + days + "=" + df1.format(x) + "元\r\n"
                    + "本金" + df1.format(z) + "  归还" + y[i] + "\r\n");

            z = z.subtract(new BigDecimal(y[i]));
            stringBuilder.append("剩余应还本金" + df1.format(z) + "\r\n");
            System.out.println("剩余应还本金" + df1.format(z));
            stringBuilder.append("---------------------------------" + "\r\n");

            System.out.println("---------------------------------");
        }
        return stringBuilder.toString();

    }

    /**
     * 字符串的日期格式的计算
     */

    int daysBetween(String smdate, String bdate) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy年MM月dd日");

        Calendar cal = Calendar.getInstance();

        cal.setTime(sdf.parse(smdate));

        long time1 = cal.getTimeInMillis();

        cal.setTime(sdf.parse(bdate));

        long time2 = cal.getTimeInMillis();

        long between_days = (time2 - time1) / (1000 * 3600 * 24);


        int days = Integer.parseInt(String.valueOf(between_days));
        System.out.println("自" + sdf1.format(sdf.parse(smdate)) + "至" + sdf1.format(sdf.parse(bdate)) + "为" + days + "天");
        stringBuilder.append("自" + sdf1.format(sdf.parse(smdate)) + "至" + sdf1.format(sdf.parse(bdate)) + "为" + days + "天\r\n");
        return days;
    }
}
