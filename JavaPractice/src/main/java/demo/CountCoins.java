package demo;

public class CountCoins {
    private Integer quarters = 25;
    private Integer dimes = 10;
    private Integer nickels = 5;
    private Integer penneis = 1;
    private Integer quartersTimes = 100 / 25;
    private Integer dimesTimes = 100 / 10;
    private Integer nickelsTimes = 100 / 5;
    private Integer penneisTimes = 100 / 1;
    private Integer quartersCount = 0;
    private Integer dimesCount = 0;
    private Integer nickelsCount = 0;
    private Integer penneisCount = 0;
    private Integer times = 0;
    private Integer sum = 0;
    private Integer standerNumber = 100;

    public Integer testCountCoins(Integer cents) {
        standerNumber = cents;
        sum(Dollor.quarters);
        System.out.println("total times: " + times);
        return times;
    }

    private void sum(Dollor dollor) {
        switch (dollor) {
            case quarters:
                if (jugdeResult(Dollor.quarters)) {
                    sum(Dollor.dimes);
                } else {
                    return;
                }
                break;
            case dimes:
                if (!jugdeResult(Dollor.dimes)) {
                    sum(Dollor.quarters);
                } else {
                    sum(Dollor.nickels);
                }
                break;
            case nickels:
                if (!jugdeResult(Dollor.nickels)) {
                    sum(Dollor.dimes);
                } else {
                    sum(Dollor.penneis);
                }

                break;
            case penneis:
                if (jugdeResult(Dollor.penneis)) {
                    penneisCount += 1;
                    sum(Dollor.penneis);
                } else {
                    sum(Dollor.nickels);
                }

                break;
        }
    }

    private boolean jugdeResult(Dollor dollor) {
        sum = quartersCount * quarters + dimes * dimesCount + nickels * nickelsCount + penneis * penneisCount;
//        if (times > 200){
//            System.out.println("sum: " + sum);
//        }
        if (sum >= standerNumber) {
            if (sum == standerNumber) {
                times += 1;
                // System.out.println( " ============times==========: " + times);
            }
            switch (dollor) {
                case quarters:
                    if (quartersCount >= quartersTimes) {
                        sum = 0;
                        return false;
                    }
                    sum = 0;
                    return true;
                case dimes:
                    if (dimesCount >= dimesTimes || sum >= 100) {
                        quartersCount += 1;
                        dimesCount = 0;
                        nickelsCount = 0;
                        penneisCount = 0;
                        sum = 0;
                        return false;
                    } else {
                        dimesCount += 1;
                        nickelsCount = 0;
                        penneisCount = 0;
                    }
                    sum = 0;
                    return true;
                case nickels:
                    if (nickelsCount >= nickelsTimes || sum >= 100) {
                        dimesCount += 1;
                        nickelsCount = 0;
                        penneisCount = 0;
                        sum = 0;
                        return false;
                    } else {
                        nickelsCount += 1;
                        penneisCount = 0;
                    }
                    sum = 0;
                    return true;
                case penneis:
                    penneisCount = 0;
                    nickelsCount += 1;
                    sum = 0;
                    return false;
            }
        }
        sum = 0;
        return true;
    }

}
