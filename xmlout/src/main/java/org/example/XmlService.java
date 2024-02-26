package org.example;

import jakarta.persistence.criteria.CriteriaBuilder;
import org.example.entity.People;
import org.example.entity.Sluch;
import org.example.entity.Usl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.Writer;




import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class XmlService {
    private static final Logger logger = LoggerFactory.getLogger(XmlService.class);
    private final Dao dao;
    @Autowired
    private ResourceLoader resourceLoader;
    public XmlService(Dao dao){
        this.dao = dao;
    }

    public String get2022Xml() throws IOException {
        for(Integer y: List.of(2022, 2023)){
            for(int m = 1; m < 13; m++){
                getXml(y, m);
            }
        }
        return "ok";
    }

    public String  getXml(Integer year, Integer month) throws IOException {
        List<People> listPeople = dao.gePeople(year, month);
        Map<String, People> mapPeople = listPeople.stream().collect(Collectors.toMap(People::getEnp, p -> p));

        List<Sluch> listSluch = dao.getListSluch(year, month);
        String encoding = "windows-1251";
        String fileName = "SZT45_"+ year.toString().substring(2, 4) + (month < 10 ? "0"+month.toString() : month.toString()) +"1.xml";
        String zipName = "SZT45_"+ year.toString().substring(2, 4) + (month < 10 ? "0"+month.toString() : month.toString()) +"1.oms";
        Integer nZap = 0;
        Writer output = new OutputStreamWriter(new FileOutputStream(fileName), encoding);
        output.write("<?xml version=\"1.0\" encoding=\"" + encoding + "\" ?>\n");
        output.write("<ZL_LIST>\n" +
                "\t<ZGLV>\n" +
                "\t\t<VERSION>1.0</VERSION>\n" +
                "\t\t<DATA>" + LocalDate.now().format(DateTimeFormatter.ofPattern("uuuu-MM-dd")) + "</DATA>\n"); //
        output.write("\t\t<FILENAME>" + fileName + "</FILENAME>\n");
        output.write("\t\t<FIRSTNAME></FIRSTNAME>\n" +
                "\t</ZGLV>\n");
        output.write("\t<SVD>\n" +
                "\t\t<YEAR>" + year + "</YEAR>\n" +
                "\t\t<MONTH>" + (month < 10 ? "0"+month.toString() : month.toString()) + "</MONTH>\n" +
                "\t</SVD>\n");

        for (Sluch s : listSluch) {

            nZap++;
            mapPeople.remove(s.getEnp());

            if(s.getProfil() == 134){s.setProfil(97);}
            if(s.getProfil() == 161 && s.getDate2().isAfter(LocalDate.of(2022, 8, 1))){s.setProfil(97);}
            if(s.getIshod() == 300){s.setIshod(304);}
            if(s.getIdsp() == 0){s.setIdsp(29);}
            if(s.getDate2().getYear()>=2019){
                if(s.getIdsp() == 17){s.setIdsp(30);}
                if(s.getIdsp() == 6){s.setIdsp(33);}
                if(s.getIdsp() == 5){s.setIdsp(33);}
                if(s.getIdsp() == 8){s.setIdsp(33);}
                if(s.getIdsp() == 11){s.setIdsp(30);}
                if(s.getIdsp() == 12){s.setIdsp(30);}
                if(s.getIdsp() == 7){s.setIdsp(32);}
                if(s.getIdsp() == 42){s.setIdsp(30);}
                if(Objects.equals(s.getDs1(), "M51.0+")){s.setDs1("M51.0");}
                if(Objects.equals(s.getDs1(), "D63.0*")){s.setDs1("D63.0");}
                if(Objects.equals(s.getDs1(), "D63.8*")){s.setDs1("D63.8");}
                if(Objects.equals(s.getDs1(), "G46.8*")){s.setDs1("G46.8");}
                if(Objects.equals(s.getDs1(), "G55.1*")){s.setDs1("G55.1");}
                if(Objects.equals(s.getDs1(), "G63.4*")){s.setDs1("G63.4");}
                if(Objects.equals(s.getDs1(), "G59*")){s.setDs1("G59");}
                if(Objects.equals(s.getDs1(), "G99*")){s.setDs1("G99");}
                if(Objects.equals(s.getDs1(), "B02.2+")){s.setDs1("B02.2");}
                if(Objects.equals(s.getDs1(), "B37.3+")){s.setDs1("B37.3");}
                if(Objects.equals(s.getDs1(), "N08.3*")){s.setDs1("N08.3");}
                if(Objects.equals(s.getDs1(), "I43.1*")){s.setDs1("I43.1");}
                if(Objects.equals(s.getDs1(), "I39.1*")){s.setDs1("I39.1");}
                if(Objects.equals(s.getDs1(), "I52*")){s.setDs1("I52");}
                if(Objects.equals(s.getDs1(), "I68.8*")){s.setDs1("I68.8");}
                if(Objects.equals(s.getDs1(), "J91*")){s.setDs1("J91");}
                if(Objects.equals(s.getDs1(), "L99.8*")){s.setDs1("L99.8");}
                if(Objects.equals(s.getDs1(), "H82*")){s.setDs1("H82");}
                if(Objects.equals(s.getDs1(), "B02.3+")){s.setDs1("B02.3");}


            }
            if(Objects.equals(s.getDs1(), "B02.0+")){s.setDs1("B02.0");}
            if(Objects.equals(s.getDs1(), "B30.1+")){s.setDs1("B30.1");}
            if(Objects.equals(s.getDs1(), "E10.4+")){s.setDs1("E10.4");}
            if(Objects.equals(s.getDs1(), "E11.2+")){s.setDs1("E11.2");}
            if(Objects.equals(s.getDs1(), "E11.3+")){s.setDs1("E11.3");}
            if(Objects.equals(s.getDs1(), "E11.4+")){s.setDs1("E11.4");}
            if(Objects.equals(s.getDs1(),"M05.3+")){s.setDs1("M05.3");}
            if(Objects.equals(s.getDs1(),"M07.3+")){s.setDs1("M07.3");}
            if(Objects.equals(s.getDs1(),"M32.1+")){s.setDs1("M32.1");}
            if(Objects.equals(s.getDs1(),"M50.0+")){s.setDs1("M50.0");}
            if(Objects.equals(s.getDs1(),"M51.0+")){s.setDs1("M51.0");}
            if(Objects.equals(s.getDs1(),"M47.0+")){s.setDs1("M47.0");}
            if(Objects.equals(s.getDs1(),"M90.3*")){s.setDs1("M90.3");}
            if(Objects.equals(s.getDs1(),"G53.0*")){s.setDs1("G53.0");}
            if(Objects.equals(s.getDs1(), "G59.8*")){s.setDs1("G59.8");}
            if(Objects.equals(s.getDs1(),"G63.2*")){s.setDs1("G63.2");}
            if(Objects.equals(s.getDs1(),"G22*")){s.setDs1("G22");}
            if(Objects.equals(s.getDs1(), "H06.2*")){s.setDs1("H06.2");}
            if(Objects.equals(s.getDs1(), "H19.3*")){s.setDs1("H19.3");}
            if(Objects.equals(s.getDs1(), "H28.0*")){s.setDs1("H28.0");}
            if(Objects.equals(s.getDs1(),"H32.0*")){s.setDs1("H32.0");}
            if(Objects.equals(s.getDs1(),"H36.0*")){s.setDs1("H36.0");}
            if(Objects.equals(s.getDs1(), "H36.8*")){s.setDs1("H36.8");}
            if(Objects.equals(s.getDs1(), "H62.2*")){s.setDs1("H62.2");}
            if(Objects.equals(s.getDs1(), "N08.2*")){s.setDs1("N08.2");}
            if(Objects.equals(s.getDs1(), "N08.3*")){s.setDs1("N08.3");}
            if(Objects.equals(s.getDs1(), "N08.4*")){s.setDs1("N08.4");}
            if(Objects.equals(s.getDs1(), "N77.1*")){s.setDs1("N77.1*");}
            if(Objects.equals(s.getDs1(), "I79.2*")){s.setDs1("I79.2");}
            if(Objects.equals(s.getDs1(), "L40.5+")){s.setDs1("L40.5");}
            if(Objects.equals(s.getDs1(), "Z00.01")){s.setDs1("Z00.0");}


            if(s.getUslOk() == 22 || s.getUslOk() == 24){s.setUslOk(2);}

            output.write("\t<ZAP>\n");
            output.write("\t\t<N_ZAP>" + nZap + "</N_ZAP>\n");
            output.write("\t\t<ENP>" + s.getEnp() + "</ENP>\n");
            output.write("\t\t<SICKLE>" + s.getSickle() + "</SICKLE>\n");
            output.write("\t\t<NDEATH>" + s.getNdeath() + "</NDEATH>\n");
            output.write("\t\t<PRIZNAK>1</PRIZNAK>\n");
            output.write("\t\t<KODTF>" + s.getKodtf() + "</KODTF>\n");
            output.write("\t\t<IDPOL>" + s.getIdpol() + "</IDPOL>\n");
            output.write("\t\t<GR>" + s.getGr() + "</GR>\n");
            output.write("\t\t<NHISTORY>" + s.getNhistory() + "</NHISTORY>\n");
            output.write("\t\t<USL_OK>" + (s.getUslOk()==7 ? 3 : s.getUslOk()) + "</USL_OK>\n");
            output.write("\t\t<VIDPOM>" + s.getVidpom() + "</VIDPOM>\n");
            output.write("\t\t<FOR_POM>" + s.getForPom() + "</FOR_POM>\n");
            output.write("\t\t<LPU>" + (s.getLpu().toString().length()==5 ? "0" + s.getLpu().toString() : s.getLpu()) + "</LPU>\n");
            if(s.getLpuPac() != null && s.getLpuPac() != 450000) {output.write("\t\t<LPU_PAC>" + s.getLpuPac() + "</LPU_PAC>\n");}
            if(s.getRslt() != null) {output.write("\t\t<RSLT>" + s.getRslt() + "</RSLT>\n");}
            output.write("\t\t<ISHOD>" + s.getIshod() + "</ISHOD>\n");
            output.write("\t\t<PROFIL>" + s.getProfil() + "</PROFIL>\n");
            output.write("\t\t<IDSP>" + s.getIdsp() + "</IDSP>\n");
            if(s.getDs0() != null) {output.write("\t\t<DS0>" + s.getDs0() + "</DS0>\n");}
            output.write("\t\t<DS1>" + s.getDs1() + "</DS1>\n");
            if(s.getDs2() != null) {output.write("\t\t<DS2>" + s.getDs2() + "</DS2>\n");}
            if(s.getDs3() != null) {output.write("\t\t<DS3>" + s.getDs3() + "</DS3>\n");}
            if(s.getCZab() != null && !(s.getDate2().getYear() < 2018 ||  (s.getDate2().getYear() == 2018 && s.getDate2().getMonthValue() < 9)))
            {output.write("\t\t<C_ZAB>" + s.getCZab() + "</C_ZAB>\n");}
            if(s.getDn() != null && s.getDn() != 0) {output.write("\t\t<DN>" + s.getDn() + "</DN>\n");}
            output.write("\t\t<DATE_1>" + s.getDate1().format(DateTimeFormatter.ofPattern("uuuu-MM-dd")) + "</DATE_1>\n");
            output.write("\t\t<DATE_2>" + s.getDate2().format(DateTimeFormatter.ofPattern("uuuu-MM-dd")) + "</DATE_2>\n");
            if(s.getNKsg() != null) {
                output.write("\t\t<KSG_KPG>\n" +
                    "\t\t\t<N_KSG>" + s.getNKsg() + "</N_KSG>\n" +
                    "\t\t\t<VER_KSG>" + s.getVerKsg() + "</VER_KSG>\n" +
                    "\t\t</KSG_KPG>\n");
            }
            if(s.getUslSet().size() > 0){

                for (Usl u : s.getUslSet()){
                    if(u.getVidVme() != "A16.31.001"){
                        output.write("\t\t<USL>\n");
                        output.write("\t\t\t<VID_VME>" + u.getVidVme() + "</VID_VME>\n");
                        output.write("\t\t</USL>\n");
                    }
                }

            }
            output.write("\t\t<DS_ONK>" + s.getDsOnk() + "</DS_ONK>\n");
            if(s.getRsltD() != null && s.getRsltD() != 0) {output.write("\t\t<RSLT_D>" + s.getRsltD() + "</RSLT_D>\n");}
            if(s.getPrDs() != null) {output.write("\t\t<PR_DS>" + s.getPrDs() + "</PR_DS>\n");}
            output.write("\t</ZAP>\n");
        }
        for (Map.Entry<String, People> entry: mapPeople.entrySet()){
            nZap++;
            output.write("\t<ZAP>\n");
            output.write("\t\t<N_ZAP>" + nZap + "</N_ZAP>\n");
            output.write("\t\t<ENP>" + entry.getKey() + "</ENP>\n");
            output.write("\t\t<SICKLE>" + entry.getValue().getSickle() + "</SICKLE>\n");
            output.write("\t\t<NDEATH>" + entry.getValue().getNdeath() + "</NDEATH>\n");
            output.write("\t\t<PRIZNAK>0</PRIZNAK>\n");
            output.write("\t\t<KODTF>45</KODTF>\n");
            output.write("\t\t<IDPOL>"+ entry.getValue().getIdpol() +"</IDPOL>\n");
            output.write("\t\t<GR>"+ entry.getValue().getGr() +"</GR>\n");
            output.write("\t</ZAP>\n");
        }
        output.write("</ZL_LIST>");

        output.flush();
        output.close();

//        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipName));
//            FileInputStream fis= new FileInputStream(fileName);)
//        {
//            ZipEntry entry1=new ZipEntry(fileName);
//            zout.putNextEntry(entry1);
//            // считываем содержимое файла в массив byte
//            byte[] buffer = new byte[fis.available()];
//            fis.read(buffer);
//            // добавляем содержимое к архиву
//            zout.write(buffer);
//            // закрываем
//            zout.closeEntry();
//        }
        return "OK";

    }

}
