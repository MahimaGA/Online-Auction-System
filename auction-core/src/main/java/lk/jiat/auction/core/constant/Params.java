package lk.jiat.auction.core.constant;

public class Params {
    public static final String CURRENCY = "LKR";
    public static final String DATE_TIME_FORMAT1 = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_TIME_FORMAT2 = "yyyy-MM-dd hh:mm a";
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static class AuctionStatus{
        public static final int SCHEDULE_STATUS_NOT_STARTED = 1;
        public static final int SCHEDULE_STATUS_ON_LIVE = 2;
        public static final int SCHEDULE_STATUS_ENDED = 3;

        private AuctionStatus(){

        }
    }

    private Params(){

    }
}
