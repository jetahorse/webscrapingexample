package com.mycompany.miwebscraping;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.List;
import org.htmlunit.WebClient;
import org.htmlunit.html.*;




/**
 *
 * @author federicoaugustotschopp
 */
public class miWebScrapingMain {

    public static void main(String[] args) {
        try (final WebClient webClient = new WebClient()) {
            webClient.getOptions().setJavaScriptEnabled(true);

            final HtmlPage page = webClient.getPage("https://www.bcra.gob.ar/PublicacionesEstadisticas/Principales_variables_datos.asp?serie=7913&detalle=Unidad%20de%20Valor%20Adquisitivo%20(UVA)%A0(en%20pesos%20con%20dos%20decimales,%20base%2031.3.2016=14.05)");
            

            final HtmlForm form = page.getFormByName("fecha");

            final HtmlInput fechaInicioInput = form.getInputByName("fecha_desde");
            final HtmlInput fechaFinInput = form.getInputByName("fecha_hasta");

            fechaInicioInput.type("01/01/2023");
            
            fechaFinInput.type("14/09/2023");

            final HtmlButton submitButton = form.getButtonByName("B1");
            final HtmlPage resultPage = submitButton.click();
           

            System.out.println(resultPage.asNormalizedText());
            final List<HtmlTable> listOfData=resultPage.getByXPath("//table");
            final HtmlTable tableUva;
            tableUva=listOfData.get(0);
            int nfilasTableUva=tableUva.getRowCount();
            for (int iter=0; iter< nfilasTableUva; iter++){
               System.out.println(tableUva.getRow(iter).getCell(0).asNormalizedText()+" - "+
                 tableUva.getRow(iter).getCell(1).asNormalizedText());
               
            }
          
        }
            catch (Exception e) {
                e.printStackTrace();
            }
    }   

}
