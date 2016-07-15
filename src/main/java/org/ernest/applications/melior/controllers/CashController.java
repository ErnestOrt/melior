package org.ernest.applications.melior.controllers;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import gnu.io.*;
import org.ernest.applications.melior.entities.Category;
import org.ernest.applications.melior.entities.IngredientUsed;
import org.ernest.applications.melior.services.CategoryService;
import org.ernest.applications.melior.services.ConsumablesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.print.*;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.print.attribute.standard.PagesPerMinute;
import javax.print.attribute.standard.PrintQuality;
import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;

@Controller
public class CashController {

	PrintService myPrinter = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_8, null)[0];

	@Autowired
	ConsumablesService consumablesService;

	@Autowired
	CategoryService categoriesService;

	@RequestMapping({ "/cash", "/" })
	public String getCashView(Model model) {

		model.addAttribute("categories", categoriesService.getCategories());
		model.addAttribute("consumables", consumablesService.getConsumables());
		return "cash";
	}

	@RequestMapping(value= "/cash/open", method = RequestMethod.GET)
	@ResponseBody
	public void openCash() throws UnsupportedCommOperationException, IOException, PortInUseException {

		System.out.println("open");

		//Configuración del Sistema -> devices
		//cmd echo hello < COM3




	}

	@RequestMapping(value= "/cash/bill", method = RequestMethod.POST)
	@ResponseBody
	public void createBill(@RequestParam(value="rateSelected") String rateSelected,
								 @RequestParam(value="tableSelected") String tableSelected,
								 @RequestParam(value="consumables") String consumables) {

		System.out.println(rateSelected + " ->>> " + tableSelected + " ->>> " + consumables);


		printBill2();

	}

	private void printBill2(){

		PrinterService printerService = new PrinterService();

		System.out.println(printerService.getPrinters());

		//print some stuff
		printerService.printString("EPSON-TM-T20II", " Canigo\n  Sant Ot 3, La Seu D'Urgell\n     973351943  973352523\n         Taula: 23 \nCanigo\n  Sant Ot 3, La Seu D'Urgell\n     973351943  973352523\n         Taula: 23 \nCanigo\n  Sant Ot 3, La Seu D'Urgell\n     973351943  973352523\n         Taula: 23 \nCanigo\n  Sant Ot 3, La Seu D'Urgell\n     973351943  973352523\n         Taula: 23 \n");

		// cut that paper!
		byte[] cutP = new byte[] { 0x1d, 'V', 1 };

		printerService.printBytes("EPSON-TM-T20II", cutP);

	}

	private void printBill() {
		Doc myDoc = new SimpleDoc(buildBillContent(), DocFlavor.STRING.TEXT_HTML, null);

		if (myPrinter != null) {
			DocPrintJob job = myPrinter.createPrintJob();
			try {

			job.print(myDoc, null);


			} catch (Exception pe) {pe.printStackTrace();}
	} else {
			System.out.println("no printer services found");
		}
	}



	private String buildBillContent() {
		return  "askdb <b>fjhafsbgkshafbfsg</b>";

		//"   _   _   _   _   _   _  \n / \\\\ / \\\\ / \\\\ / \\\\ / \\\\ / \\\\ \\n ( C | a | n | i | g | ó )\\n  \\\\_/ \\\\_/ \\\\_/ \\\\_/ \\\\_/ \\\\_/\\n\\n  Sant Ot 3, La Seu D'Urgell\\n     973351943  973352523\\n\\n         Taula: 23 \\n\\n" +
		//"  / \\ / \\ / \\ / \\ / \\ / \\ \n" +
		//" ( C | a | n | i | g | ó )\n" +
		//"  \\_/ \\_/ \\_/ \\_/ \\_/ \\_/\n\n" +
		//"  Sant Ot 3, La Seu D'Urgell\n" +
		//"    973351943  973352523\n\n" +
		//"         Taula: 23 \n\n" +
		//"Quant.\tDescripció\tPreu\n\n" +
		//"1\tCocacola\t1.85\n"+
		//"1\tFanta llim\t1.75\n"+
		//"1\tCombi #1\t5.85\n"+
		//"1\tCombi #2\t7.85\n\n"+
		//"\nBase: 15.65\n"+
		//"IVA: 3.32\n"+
		//"TOTAL: 19.14\n\n"+
		//"Grácies per la seva visita";
	}
}


class PrinterService implements Printable {

	public List<String> getPrinters(){

		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

		PrintService printServices[] = PrintServiceLookup.lookupPrintServices(
				flavor, pras);

		List<String> printerList = new ArrayList<String>();
		for(PrintService printerService: printServices){
			printerList.add( printerService.getName());
		}

		return printerList;
	}

	@Override
	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}

		/*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 */
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		/* Now we perform our rendering */

		g.setFont(new Font("Roman", 0, 8));
		g.drawString("Hello world !", 0, 10);

		return PAGE_EXISTS;
	}

	public void printString(String printerName, String text) {

		// find the printService of name printerName
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

		PrintService printService[] = PrintServiceLookup.lookupPrintServices(
				flavor, pras);
		PrintService service = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_8, null)[0];

		DocPrintJob job = service.createPrintJob();

		try {

			byte[] bytes;

			// important for umlaut chars
			bytes = text.getBytes("CP437");

			Doc doc = new SimpleDoc(bytes, flavor, null);


			job.print(doc, null);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void printBytes(String printerName, byte[] bytes) {

		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

		PrintService printService[] = PrintServiceLookup.lookupPrintServices(
				flavor, pras);
		PrintService service = PrintServiceLookup.lookupPrintServices(DocFlavor.INPUT_STREAM.TEXT_PLAIN_UTF_8, null)[0];

		DocPrintJob job = service.createPrintJob();

		try {

			Doc doc = new SimpleDoc(bytes, flavor, null);

			job.print(doc, null);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}


}