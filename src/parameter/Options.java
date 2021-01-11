package parameter;

public class Options {
    public String flag;
	public String value;

    Options(String flag){
        this.flag = flag; 
    }

    Options(String flag, String value){
        this.flag = flag;
        this.value = value;
    }
}
