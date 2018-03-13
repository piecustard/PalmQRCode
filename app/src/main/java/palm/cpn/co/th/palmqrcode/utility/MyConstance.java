package palm.cpn.co.th.palmqrcode.utility;

/**
 * Created by Lorien on 08/03/2018.
 */

public class MyConstance {

    private String urlGetFoodWhereQRcode = "http://androidthai.in.th/cent/getFoodWhereQRcodePalm.php";
    private String urlReadAllFood = "http://androidthai.in.th/cent/getAllFood.php";
    private String urlAddUser = "http://androidthai.in.th/cent/addDataPalm.php";
    private String urlReadAllUser = "http://androidthai.in.th/cent/getAllDataPalm.php";

    private String[] columnFoodStrings = new String[]{"id", "Category", "NameFood", "Price", "Detail", "ImagePath", "QRcode"};
    private String[] columnUserTableStrings = new String[]{"id", "Name", "User", "Password"};

    public String[] getColumnFoodStrings() {
        return columnFoodStrings;
    }

    public String[] getColumnUserTableStrings() {
        return columnUserTableStrings;
    }

    public String getUrlReadAllFood() { // Getter Medthod
        return urlReadAllFood;
    }

    public String getUrlAddUser() { // Getter Medthod
        return urlAddUser;
    }

    public String getUrlGetFoodWhereQRcode() {
        return urlGetFoodWhereQRcode;
    }

    public String getUrlReadAllUser() {
        return urlReadAllUser;
    } // Getter Medthod
}
