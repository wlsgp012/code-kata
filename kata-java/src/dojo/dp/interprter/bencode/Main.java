package dojo.dp.interprter.bencode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Map<StringElement, BencodeElement> mainStructure = new HashMap<StringElement, BencodeElement>();
        mainStructure.put(new StringElement("user"), new StringElement("Bertie"));
        mainStructure.put(new StringElement("number_of_downloaded_torrents"), new IntegerElement(623));
        mainStructure.put(new StringElement("number_of_uploaded_torrents"), new IntegerElement(0));
        mainStructure.put(new StringElement("donation_in_dollars"), new IntegerElement(0));
        mainStructure.put(new StringElement("preffered_categories"), new ListElement(Arrays.asList(new StringElement("porn"), new StringElement("murder"), new StringElement("scala"), new StringElement("pokemons"))));
        BencodeElement top = new DictionaryElement(mainStructure);
        String bencodedString = top.interpret();

        System.out.println(bencodedString);
    }
}
