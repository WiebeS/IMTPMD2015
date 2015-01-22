package ws.marioenco.Models;

/**
 * Created by Wiebe on 1/19/2015.
 */
public class InformatieServiceModel {

    //singleton pattern
    private static InformatieServiceModel _instance;

    public static InformatieServiceModel getInstance() {
        if (_instance == null)
            _instance = new InformatieServiceModel();

        return _instance;
    }
    private String infoService;

    public String getinfoService() {
        return infoService;
    }

    public void setinfoService(String infoService) {
        this.infoService = infoService;
    }

    public void setInfoServiceHardCoded(String string){

//        // set info service adv de pos
//        if(pos ==0){
//            this.infoService ="Iedereen vindt het niets meer dan normaal dat als u de WC doorspoelt het water netjes het riool instroomt. Maar wat als dit nu eens niet gebeurt? U spoelt het toilet door maar het water komt omhoog in de WC pot of het water blijft staan? Neem ook hiervoor met spoed contact op met onze loodgieters!";
//        }
//        if(pos ==1) {
//        }
//        this.infoService = "Reparatie nodig aan uw dak? Veelal hoeft het dak niet te worden vervangen maar kan deze gewoon nog worden gerepareerd. In sommige gevallen is het echter goedkoper om het geheel te vervangen. In beide situaties brengen we graag advies en een vrijblijvende offerte uit." ;
//        if(pos ==2) {
//            this.infoService = "Is uw prinses gevangen genomen door een plaag Goombas? Kunt u maar niet het juiste kasteel vinden? Wij zijn gespecialiseerd in het vinden van gevangen genomen prinsessen. Dankzij onze uitgebreide ervaring van het riolering netwerk kunnen wij snel ter plaatse zijn.";
//        }
        this.infoService = string;
    }
}