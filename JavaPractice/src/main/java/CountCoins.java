public class CountCoins {
    private Integer quarters = 25;
    private Integer dimes = 10;
    private Integer nickels = 5;
    private Integer penneis = 1;
    private Integer quartersTimes = 100/25;
    private Integer dimesTimes = 100/10;
    private Integer nickelsTimes = 100/5;
    private Integer penneisTimes = 100/1;
    private Integer quartersCount = 0;
    private Integer dimesCount = 0;
    private Integer nickelsCount = 0;
    private Integer penneisCount = 0;
    private Integer times = 0;
    private Integer sum = 0;

    public  void  testCountCoins() {
        sum(Dollor.quarters);
        System.out.println("total times: " + times);
    }

    private void  sum(Dollor dollor) {

        switch (dollor){
            case quarters:
                if (jugdeResult(Dollor.quarters)){
                    sum(Dollor.dimes);
                }else {
                    return;
                }
                break;
            case dimes:
                if (!jugdeResult(Dollor.dimes)){
                    sum(Dollor.quarters);
                }else {
                    sum(Dollor.nickels);
                }
                break;
            case nickels:
                if (!jugdeResult(Dollor.nickels)){
                    sum(Dollor.dimes);
                }else {
                    sum(Dollor.penneis);
                }

                break;
            case penneis:
                if (jugdeResult(Dollor.penneis)){
                    penneisCount +=1;
                    sum(Dollor.penneis);
                }else {
                    sum(Dollor.nickels);
                }

                break;
        }
    }

    private  boolean jugdeResult(Dollor dollor) {
        sum = quartersCount * quarters + dimes *dimesCount + nickels * nickelsCount + penneis * penneisCount;
        if (sum >= 100){
            sum = 0;
            if (sum == 100){
                System.out.println(quartersCount+ " * "  + quarters+ " +  " + dimes+  " * " +dimesCount+" +  " + nickels+  " * " +nickelsCount +" +  " + penneis +  " * " + penneisCount);
                times += 1;
            }
            switch (dollor){
                case quarters:
                    if (quartersCount >= quartersTimes){
                        return false;
                    }
                    return true;
                case dimes:
                    if (dimesCount >= dimesTimes){
                        quartersCount += 1;
                        dimesCount = 0;
                        nickelsCount = 0;
                        penneisCount = 0;
                        return  false;
                    }else {
                        dimesCount += 1;
                        nickelsCount = 0;
                        penneisCount = 0;
                    }
                    return true;
                case nickels:
                    if (nickelsCount >= nickelsTimes){
                        dimesCount += 1;
                        nickelsCount = 0;
                        penneisCount = 0;
                        return  false;
                    }else {
                        nickelsCount += 1;
                        penneisCount = 0;
                    }
                    return true;
                case penneis:
                    penneisCount  = 0;
                    nickelsCount += 1;
                    return false;
            }
        }
        return  true;
    }

}
