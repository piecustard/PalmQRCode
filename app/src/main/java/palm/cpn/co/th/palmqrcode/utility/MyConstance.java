package palm.cpn.co.th.palmqrcode.utility;

/**
 * Created by Lorien on 08/03/2018.
 */

public class MyConstance {

    private String urlAddUser = "http://androidthai.in.th/cent/addDataPalm.php";
    private String urlReadAllUser = "http://androidthai.in.th/cent/getAllDataPalm.php";

    private String[] columUserTableStrings = new String[]{"id", "Name", "User", "Password"};

    public String[] getColumUserTableStrings() {
        return columUserTableStrings;
    }

    public String getUrlAddUser() {
        return urlAddUser;
    }

    public String getUrlReadAllUser() {
        return urlReadAllUser;
    }
}
