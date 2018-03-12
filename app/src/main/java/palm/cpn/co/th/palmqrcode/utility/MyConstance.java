package palm.cpn.co.th.palmqrcode.utility;

/**
 * Created by Lorien on 08/03/2018.
 */

public class MyConstance {

    private String urlReadAllFood = "http://androidthai.in.th/cent/getAllFood.php";
    private String urlAddUser = "http://androidthai.in.th/cent/addDataPalm.php";
    private String urlReadAllUser = "http://androidthai.in.th/cent/getAllDataPalm.php";

    private String[] columnUserTableStrings = new String[]{"id", "Name", "User", "Password"};

    public String[] getColumnUserTableStrings() {
        return columnUserTableStrings;
    }

    public String getUrlReadAllFood() { // Getter Medthod
        return urlReadAllFood;
    }

    public String getUrlAddUser() { // Getter Medthod
        return urlAddUser;
    }

    public String getUrlReadAllUser() {
        return urlReadAllUser;
    } // Getter Medthod
}
