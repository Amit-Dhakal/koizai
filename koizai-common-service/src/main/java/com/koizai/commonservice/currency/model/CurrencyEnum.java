package com.koizai.commonservice.currency.model;



import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;


@RequiredArgsConstructor
@Getter
public enum CurrencyEnum {
    USD("US Dollar", "$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    CAD("Canadian Dollar", "CA$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    EUR("Euro", "€", "€", NumberFormat.getInstance(Locale.ENGLISH)),
    AED("United Arab Emirates Dirham", "AED", "د.إ.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    AFN("Afghan Afghani", "Af", "؋", NumberFormat.getInstance(Locale.ENGLISH)),
    ALL("Albanian Lek", "ALL", "Lek", NumberFormat.getInstance(Locale.ENGLISH)),
    AMD("Armenian Dram", "AMD", "դր.", NumberFormat.getInstance(Locale.ENGLISH)),
    ARS("Argentine Peso", "AR$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    AUD("Australian Dollar", "AU$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    AZN("Azerbaijani Manat", "man.", "ман.", NumberFormat.getInstance(Locale.ENGLISH)),
    BAM("Bosnia-Herzegovina Convertible Mark", "KM", "KM", NumberFormat.getInstance(Locale.ENGLISH)),
    BDT("Bangladeshi Taka", "Tk", "৳", NumberFormat.getInstance(Locale.ENGLISH)),
    BGN("Bulgarian Lev", "BGN", "лв.", NumberFormat.getInstance(Locale.ENGLISH)),
    BHD("Bahraini Dinar", "BD", "د.ب.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    BIF("Burundian Franc", "FBu", "FBu", NumberFormat.getInstance(Locale.ENGLISH)),
    BND("Brunei Dollar", "BN$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    BOB("Bolivian Boliviano", "Bs", "Bs", NumberFormat.getInstance(Locale.ENGLISH)),
    BRL("Brazilian Real", "R$", "R$", NumberFormat.getInstance(Locale.ENGLISH)),
    BWP("Botswanan Pula", "BWP", "P", NumberFormat.getInstance(Locale.ENGLISH)),
    BYN("Belarusian Ruble", "Br", "руб.", NumberFormat.getInstance(Locale.ENGLISH)),
    BZD("Belize Dollar", "BZ$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    CDF("Congolese Franc", "CDF", "FrCD", NumberFormat.getInstance(Locale.ENGLISH)),
    CHF("Swiss Franc", "CHF", "CHF", NumberFormat.getInstance(Locale.ENGLISH)),
    CLP("Chilean Peso", "CL$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    CNY("Chinese Yuan", "CN¥", "CN¥", NumberFormat.getInstance(Locale.ENGLISH)),
    COP("Colombian Peso", "CO$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    CRC("Costa Rican Colón", "₡", "₡", NumberFormat.getInstance(Locale.ENGLISH)),
    CVE("Cape Verdean Escudo", "CV$", "CV$", NumberFormat.getInstance(Locale.ENGLISH)),
    CZK("Czech Republic Koruna", "Kč", "Kč", NumberFormat.getInstance(Locale.ENGLISH)),
    DJF("Djiboutian Franc", "Fdj", "Fdj", NumberFormat.getInstance(Locale.ENGLISH)),
    DKK("Danish Krone", "Dkr", "kr", NumberFormat.getInstance(Locale.ENGLISH)),
    DOP("Dominican Peso", "RD$", "RD$", NumberFormat.getInstance(Locale.ENGLISH)),
    DZD("Algerian Dinar", "DA", "د.ج.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    //    EEK("Estonian Kroon", "Ekr", "kr", NumberFormat.getInstance(Locale.ENGLISH)),
    EGP("Egyptian Pound", "EGP", "ج.م.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    ERN("Eritrean Nakfa", "Nfk", "Nfk", NumberFormat.getInstance(Locale.ENGLISH)),
    ETB("Ethiopian Birr", "Br", "Br", NumberFormat.getInstance(Locale.ENGLISH)),
    GBP("British Pound Sterling", "£", "£", NumberFormat.getInstance(Locale.ENGLISH)),
    GEL("Georgian Lari", "GEL", "GEL", NumberFormat.getInstance(Locale.ENGLISH)),
    GHS("Ghanaian Cedi", "GH₵", "GH₵", NumberFormat.getInstance(Locale.ENGLISH)),
    GNF("Guinean Franc", "FG", "FG", NumberFormat.getInstance(Locale.ENGLISH)),
    GTQ("Guatemalan Quetzal", "GTQ", "Q", NumberFormat.getInstance(Locale.ENGLISH)),
    HKD("Hong Kong Dollar", "HK$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    HNL("Honduran Lempira", "HNL", "L", NumberFormat.getInstance(Locale.ENGLISH)),
    HRK("Croatian Kuna", "kn", "kn", NumberFormat.getInstance(Locale.ENGLISH)),
    HUF("Hungarian Forint", "Ft", "Ft", NumberFormat.getInstance(Locale.ENGLISH)),
    IDR("Indonesian Rupiah", "Rp", "Rp", NumberFormat.getInstance(Locale.ENGLISH)),
    ILS("Israeli New Sheqel", "₪", "₪", NumberFormat.getInstance(Locale.ENGLISH)),
    INR("Indian Rupee", "Rs", "টকা", NumberFormat.getInstance(Locale.ENGLISH)),
    IQD("Iraqi Dinar", "IQD", "د.ع.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    IRR("Iranian Rial", "IRR", "﷼", NumberFormat.getInstance(Locale.ENGLISH)),
    ISK("Icelandic Króna", "Ikr", "kr", NumberFormat.getInstance(Locale.ENGLISH)),
    JMD("Jamaican Dollar", "J$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    JOD("Jordanian Dinar", "JD", "د.أ.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    JPY("Japanese Yen", "¥", "￥", NumberFormat.getInstance(Locale.ENGLISH)),
    KES("Kenyan Shilling", "Ksh", "Ksh", NumberFormat.getInstance(Locale.ENGLISH)),
    KHR("Cambodian Riel", "KHR", "៛", NumberFormat.getInstance(Locale.ENGLISH)),
    KMF("Comorian Franc", "CF", "FC", NumberFormat.getInstance(Locale.ENGLISH)),
    KRW("South Korean Won", "₩", "₩", NumberFormat.getInstance(Locale.ENGLISH)),
    KWD("Kuwaiti Dinar", "KD", "د.ك.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    KZT("Kazakhstani Tenge", "KZT", "тңг.", NumberFormat.getInstance(Locale.ENGLISH)),
    LBP("Lebanese Pound", "LB£", "ل.ل.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    LKR("Sri Lankan Rupee", "SLRs", "SL Re", NumberFormat.getInstance(Locale.ENGLISH)),
    LTL("Lithuanian Litas", "Lt", "Lt", NumberFormat.getInstance(Locale.ENGLISH)),
    LVL("Latvian Lats", "Ls", "Ls", NumberFormat.getInstance(Locale.ENGLISH)),
    LYD("Libyan Dinar", "LD", "د.ل.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    MAD("Moroccan Dirham", "MAD", "د.م.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    MDL("Moldovan Leu", "MDL", "MDL", NumberFormat.getInstance(Locale.ENGLISH)),
    MGA("Malagasy Ariary", "MGA", "MGA", NumberFormat.getInstance(Locale.ENGLISH)),
    MKD("Macedonian THE FORMER YUGOSLAV REPUBLIC", "MKD", "MKD", NumberFormat.getInstance(Locale.ENGLISH)),
    MMK("Myanma Kyat", "MMK", "K", NumberFormat.getInstance(Locale.ENGLISH)),
    MOP("Macanese Pataca", "MOP$", "MOP$", NumberFormat.getInstance(Locale.ENGLISH)),
    MUR("Mauritian Rupee", "MURs", "MURs", NumberFormat.getInstance(Locale.ENGLISH)),
    MXN("Mexican Peso", "MX$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    MYR("Malaysian Ringgit", "RM", "RM", NumberFormat.getInstance(Locale.forLanguageTag("ms"))),
    MZN("Mozambican Metical", "MTn", "MTn", NumberFormat.getInstance(Locale.ENGLISH)),
    NAD("Namibian Dollar", "N$", "N$", NumberFormat.getInstance(Locale.ENGLISH)),
    NGN("Nigerian Naira", "₦", "₦", NumberFormat.getInstance(Locale.ENGLISH)),
    NIO("Nicaraguan Córdoba", "C$", "C$", NumberFormat.getInstance(Locale.ENGLISH)),
    NOK("Norwegian Krone", "Nkr", "kr", NumberFormat.getInstance(Locale.ENGLISH)),
    NPR("Nepalese Rupee", "NPRs", "नेरू", NumberFormat.getInstance(Locale.ENGLISH)),
    NZD("New Zealand Dollar", "NZ$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    OMR("Omani Rial", "OMR", "ر.ع.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    PAB("Panamanian Balboa", "B/.", "B/.", NumberFormat.getInstance(Locale.ENGLISH)),
    PEN("Peruvian Nuevo Sol", "S/.", "S/.", NumberFormat.getInstance(Locale.ENGLISH)),
    PHP("Philippine Peso", "₱", "₱", NumberFormat.getInstance(Locale.ENGLISH)),
    PKR("Pakistani Rupee", "PKRs", "₨", NumberFormat.getInstance(Locale.ENGLISH)),
    PLN("Polish Zloty", "zł", "zł", NumberFormat.getInstance(Locale.ENGLISH)),
    PYG("Paraguayan Guarani", "₲", "₲", NumberFormat.getInstance(Locale.ENGLISH)),
    QAR("Qatari Rial", "QR", "ر.ق.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    RON("Romanian Leu", "RON", "RON", NumberFormat.getInstance(Locale.ENGLISH)),
    RSD("Serbian Dinar", "din.", "дин.", NumberFormat.getInstance(Locale.ENGLISH)),
    RUB("Russian Ruble", "RUB", "₽.", NumberFormat.getInstance(Locale.ENGLISH)),
    RWF("Rwandan Franc", "RWF", "FR", NumberFormat.getInstance(Locale.ENGLISH)),
    SAR("Saudi Riyal", "SR", "ر.س.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    SDG("Sudanese Pound", "SDG", "SDG", NumberFormat.getInstance(Locale.ENGLISH)),
    SEK("Swedish Krona", "Skr", "kr", NumberFormat.getInstance(Locale.ENGLISH)),
    SGD("Singapore Dollar", "S$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    SOS("Somali Shilling", "Ssh", "Ssh", NumberFormat.getInstance(Locale.ENGLISH)),
    SYP("Syrian Pound", "SY£", "ل.س.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    THB("Thai Baht", "฿", "฿", NumberFormat.getInstance(Locale.ENGLISH)),
    TND("Tunisian Dinar", "DT", "د.ت.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    TOP("Tongan Paʻanga", "T$", "T$", NumberFormat.getInstance(Locale.ENGLISH)),
    TRY("Turkish Lira", "TL", "TL", NumberFormat.getInstance(Locale.ENGLISH)),
    TTD("Trinidad and Tobago Dollar", "TT$", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    TWD("New Taiwan Dollar", "NT$", "NT$", NumberFormat.getInstance(Locale.ENGLISH)),
    TZS("Tanzanian Shilling", "TSh", "TSh", NumberFormat.getInstance(Locale.ENGLISH)),
    UAH("Ukrainian Hryvnia", "₴", "₴", NumberFormat.getInstance(Locale.ENGLISH)),
    UGX("Ugandan Shilling", "USh", "USh", NumberFormat.getInstance(Locale.ENGLISH)),
    UYU("Uruguayan Peso", "$U", "$", NumberFormat.getInstance(Locale.ENGLISH)),
    UZS("Uzbekistan Som", "UZS", "UZS", NumberFormat.getInstance(Locale.ENGLISH)),
    VEF("Venezuelan Bolívar", "Bs.F.", "Bs.F.", NumberFormat.getInstance(Locale.ENGLISH)),
    VND("Vietnamese Dong", "₫", "₫", NumberFormat.getInstance(Locale.ENGLISH)),
    XAF("CFA Franc BEAC", "FCFA", "FCFA", NumberFormat.getInstance(Locale.ENGLISH)),
    XOF("CFA Franc BCEAO", "CFA", "CFA", NumberFormat.getInstance(Locale.ENGLISH)),
    YER("Yemeni Rial", "YR", "ر.ي.‏", NumberFormat.getInstance(Locale.ENGLISH)),
    ZAR("South African Rand", "R", "R", NumberFormat.getInstance(Locale.ENGLISH)),
    ZMK("Zambian Kwacha", "ZK", "ZK", NumberFormat.getInstance(Locale.ENGLISH)),
    ZWL("Zimbabwean Dollar", "ZWL$", "ZWL$", NumberFormat.getInstance(Locale.ENGLISH));


    private final String country;
    private final String symbol;
    private final String symbolNative;
    private final NumberFormat numberFormat;

    public static CurrencyEnum getDefaultCurrency() {
        return CurrencyEnum.USD;
    }


    public static CurrencyEnum getByCountry(String country) {
        return Arrays.stream(CurrencyEnum.values())
                .filter(currency -> currency.country.equalsIgnoreCase(country))
                .findFirst()
                .orElse(null);
    }

}