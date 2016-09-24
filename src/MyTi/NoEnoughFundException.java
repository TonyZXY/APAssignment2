package MyTi;

/**
 * Project APAssignment2
 * Created by TonyZheng on 24/9/16.
 */
public class NoEnoughFundException extends Exception{
    public NoEnoughFundException(String err){
        super(err);
    }

    public String getMessage(){
        return super.getMessage();
    }
}
