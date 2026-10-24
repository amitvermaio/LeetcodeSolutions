class Solution {
    public int findDayOfYear(String date, int[] cumMonthDays) {
        int month = Integer.parseInt(date.substring(0, 2)); // MM
        int days = Integer.parseInt(date.substring(3)); // DD

        return cumMonthDays[month - 1] + days;
    }

    public int countDaysTogether(String arriveAlice, String leaveAlice,
            String arriveBob, String leaveBob) {

        int[] monthDays = { 31, 28, 31, 30, 31, 30,
                31, 31, 30, 31, 30, 31 };

        int[] cumMonthDays = new int[13];

        cumMonthDays[1] = 31;

        for (int i = 2; i <= 12; i++) {
            cumMonthDays[i] = cumMonthDays[i - 1] + monthDays[i - 1];
        }

        int arriveAliceDay = findDayOfYear(arriveAlice, cumMonthDays);
        int leaveAliceDay = findDayOfYear(leaveAlice, cumMonthDays);
        int arriveBobDay = findDayOfYear(arriveBob, cumMonthDays);
        int leaveBobDay = findDayOfYear(leaveBob, cumMonthDays);

        int overlapDays = Math.min(leaveAliceDay, leaveBobDay)
                - Math.max(arriveAliceDay, arriveBobDay) + 1;

        return Math.max(0, overlapDays);
    }
}