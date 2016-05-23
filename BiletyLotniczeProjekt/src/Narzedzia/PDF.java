package Narzedzia;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import java.awt.Desktop;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PDF 
{
    private final String DODAJ_PDF = "INSERT INTO potwierdzenia_pdf ( PTW_ZKP_ID, PTW_PDF ) VALUES (?,?)";
    private final String POBIERZ_PDF = "SELECT PTW_PDF FROM potwierdzenia_pdf WHERE PTW_ZKP_ID=?";
    
    Connection connection = null;
    DBConnector dbConnector = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    
    public PDF()
    {
        dbConnector = new DBConnector();
    }
    
    private boolean exportToHTML( String dataLotu, String cel, String cena, String rzadIMiejsce, String dataZakupu ) throws Exception
    {
		StringBuilder buffer=new StringBuilder();
		File outputFile=new File("files\\potwierdzenie.html");
		BufferedWriter bfw=new BufferedWriter(new FileWriter(outputFile));
		
		buffer.append(" <?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<?xml-stylesheet type=\"text/css\" href=\"style.css\"?>\n" +
                    "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\" \"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n" +
                    "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"pl\">\n" +
                    "<head>\n" +
                    "  <title>Potwierdzenie</title>\n" +
                    "</head>\n" +
                    "<body>\n" +
                    "	<p>\n" +
                    "       Potwierdzenie zakupu biletu lotniczego.\n" +
                    "    </p>\n" +
                    "	<br />\n" +
                    "	<br />\n" +
                    "	<p>\n" +
                    "       Data lotu: "+ dataLotu +"\n" +
                    "    </p>\n" +
                    "	<p>\n" +
                    "       Cel: "+ cel +"\n" +
                    "    </p>\n" +
                    "	<p>\n" +
                    "       Cena: "+ cena +"\n" +
                    "    </p>\n" +
                    "	<p>\n" +
                    "       Rząd i miejsce:  "+ rzadIMiejsce +"\n" +
                    "    </p>\n" +
                    "	<p>\n" +
                    "       Data zakupu:  "+ dataZakupu +"\n" +
                    "    </p>\n" +
                    "</body>\n" +
                    "</html>"
                );
		bfw.write(buffer.toString());
		bfw.close();
		return true;
	}
    public boolean stworzPDF( String IDZakupu, String dataLotu, String cel, String cena, String rzadIMiejsce, String dataZakupu ) throws DocumentException, IOException, Exception
    {
        if (exportToHTML( dataLotu, cel, cena, rzadIMiejsce, dataZakupu ))
        {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("files\\potwierdzenie.pdf"));
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("files\\potwierdzenie.html"));
            document.close();
            Desktop.getDesktop().open(new File("files\\potwierdzenie.pdf"));
            Path potwierdzenie_path = Paths.get("files", "potwierdzenie.html");
            List<String> ls = Files.readAllLines( potwierdzenie_path, Charset.forName("ISO-8859-1") );
            StringBuilder sb = new StringBuilder();
            for( String s : ls )
            {
                sb.append(s);
            }
            dodajPDF( IDZakupu, sb.toString() );
            
            return true;
        }
        else
        {
            return false;
        }
    }
    public void pokazPDF(String IDZakupu) throws FileNotFoundException, DocumentException, IOException, SQLException
    {
        String pdf = null;
        try
        {
            connection = dbConnector.setConnection();
            ps = connection.prepareStatement( POBIERZ_PDF );
            ps.setObject(1, IDZakupu);
            
            rs = ps.executeQuery();
            while(rs.next())
            {
                pdf = rs.getString(1);
            }
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("files\\potwierdzenie.pdf"));
            document.open();
            try(  PrintWriter out = new PrintWriter( "files\\potwierdzenie.html" )  )
            {
                out.println( pdf );
            }
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new FileInputStream("files\\potwierdzenie.html"));
            document.close();
            Desktop.getDesktop().open(new File("files\\potwierdzenie.pdf"));
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            connection.close();
            ps.close();
        }
    }
    
    private void dodajPDF( String IDZakupu, String tresc ) throws SQLException
    {
        try
        {
            connection = dbConnector.setConnection();
            ps = connection.prepareStatement( DODAJ_PDF );
            ps.setObject(1, IDZakupu);
            ps.setObject(2, tresc);
            
            ps.executeUpdate();
        }
        catch( SQLException e )
        {
            e.printStackTrace();
        }
        finally
        {
            connection.close();
            ps.close();
        }
    }
}
