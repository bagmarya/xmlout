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
        for(Integer y: List.of(2017, 2018, 2019, 2020, 2021, 2022, 2023)){
            for(int m = 1; m < 13; m++){
                getXml(y, m);
            }
        }
        return "ok";
    }

    public String  getXml(Integer year, Integer month) throws IOException {
        List<People> listPeople = dao.gePeople();
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
            output.write("\t\t<LPU>" + s.getLpu() + "</LPU>\n");
            if(s.getLpuPac() != null) {output.write("\t\t<LPU_PAC>" + s.getLpuPac() + "</LPU_PAC>\n");}
            if(s.getRslt() != null) {output.write("\t\t<RSLT>" + s.getRslt() + "</RSLT>\n");}
            output.write("\t\t<ISHOD>" + s.getIshod() + "</ISHOD>\n");
            output.write("\t\t<PROFIL>" + s.getProfil() + "</PROFIL>\n");
            output.write("\t\t<IDSP>" + s.getIdsp() + "</IDSP>\n");
            if(s.getDs0() != null) {output.write("\t\t<DS0>" + s.getDs0() + "</DS0>\n");}
            output.write("\t\t<DS1>" + s.getDs1() + "</DS1>\n");
            if(s.getDs2() != null) {output.write("\t\t<DS2>" + s.getDs2() + "</DS2>\n");}
            if(s.getDs3() != null) {output.write("\t\t<DS3>" + s.getDs3() + "</DS3>\n");}
            if(s.getCZab() != null) {output.write("\t\t<C_ZAB>" + s.getCZab() + "</C_ZAB>\n");}
            if(s.getDn() != null) {output.write("\t\t<DN>" + s.getDn() + "</DN>\n");}
            output.write("\t\t<DATE_1>" + s.getDate1().format(DateTimeFormatter.ofPattern("uuuu-MM-dd")) + "</DATE_1>\n");
            output.write("\t\t<DATE_2>" + s.getDate2().format(DateTimeFormatter.ofPattern("uuuu-MM-dd")) + "</DATE_2>\n");
            if(s.getNKsg() != null) {
                output.write("\t\t<KSG_KPG>\n" +
                    "\t\t\t<N_KSG>" + s.getNKsg() + "</N_KSG>\n" +
                    "\t\t\t<VER_KSG>" + s.getVerKsg() + "</VER_KSG>\n" +
                    "\t\t</KSG_KPG>\n");
            }
            if(s.getUslSet().size() > 0){
                output.write("\t\t<USL>\n");
                for (Usl u : s.getUslSet()){
                    output.write("\t\t\t<VID_VME>" + u.getVidVme() + "</VID_VME>\n");
                }
                output.write("\t\t</USL>\n");
            }
            output.write("\t\t<DS_ONK>" + s.getDsOnk() + "</DS_ONK>\n");
            if(s.getRsltD() != null) {output.write("\t\t<RSLT_D>" + s.getRsltD() + "</RSLT_D>\n");}
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
            output.write("\t</ZAP>\n");
        }
        output.write("</ZL_LIST>");

        output.flush();
        output.close();

        try(ZipOutputStream zout = new ZipOutputStream(new FileOutputStream(zipName));
            FileInputStream fis= new FileInputStream(fileName);)
        {
            ZipEntry entry1=new ZipEntry(fileName);
            zout.putNextEntry(entry1);
            // считываем содержимое файла в массив byte
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            // добавляем содержимое к архиву
            zout.write(buffer);
            // закрываем
            zout.closeEntry();
        }
        return "OK";

    }

}
