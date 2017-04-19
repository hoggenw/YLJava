public  class  sonCalculation extends  Calculation {
    public  void  multiplication(int x, int y ){
        z = x * y;
        System.out.println("The product of the given numbers:"+z);
    }
    public  void  addition(int x, int y) {
        super.addition(x, y);
        z = x + y + y;
        System.out.println("The sum of the given numbers in son :"+z + "\n");
    }
}
