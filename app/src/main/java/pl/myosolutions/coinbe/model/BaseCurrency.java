package pl.myosolutions.coinbe.model;

/**
 * Created by Jacek on 2017-10-02.
 */

public enum BaseCurrency {
    LYNX("LYNX", "LynxCoin"),
    BTC("BTC", "Bitcoin"),
    BCC("BCC", "Bitcoin Cash"),
    ETH("ETH", "Ethereum"),
    ETC("ETC", "Ethereum Classic"),
    XEC("XEC", "Nem"),
    ZEC("ZEC", "Zcash"),
    LTC("LTC", "Litecoin"),
    DASH("DASH", "Dash"),
    DOGE("DOGE", "Dogecoin"),
    XMR("XMR", "Monero"),
    BCN("BCN", "Bytecoin"),
    BEN("BEN", "BentynCoin"),
    GNT("GNT", "Golem Network Token"),
    WAVES("WAVES", "Waves"),
    WAP("WAP", "WapniakCoin"),
    KBC("KBC", "KabutoCoin"),
    BEAT("BEAT", "Beatcoin Things"),
    LSK("LSK", "Lisk"),
    BKCOIN("BKCOIN", "BEZ KANAŁU Coin"),
    EOS("EOS", "EOS"),
    XEM("XEM", "NEM"),
    SWT("SWT", "SwarmCity Token"),
    LSC("LSC", "Lifestylecoin"),
    PIVX("PIVX", "PIVX"),
    OMG("OMG", "OMG");

    private String id;
    private String name;

    BaseCurrency(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
