/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ukma.bigdata.yupro.apriori.utils.csv;

import com.ukma.bigdata.yupro.apriori.utils.OneToOneLazyJoiner;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.junit.Test;

/**
 *
 * @author michael
 */
public class OrderProductToProductLazyJoinerTest {

    @Test
    public void testGetJoin() throws IOException {
        String productsPath = this.getClass().getResource("/csv/products.csv").
                getPath();
//        System.out.println(productsPath);
        CsvProductParser instance = new CsvProductParser(productsPath);
        Map<Object, Product> products = instance.parseAll().stream()
                .collect(Collectors.toMap(Product::getProductId, p -> p));

        String orderProductsString = this.getClass().getResource(
                "/csv/order_products__prior.csv").getPath();
        CsvOrderProductParser copp = new CsvOrderProductParser(
                orderProductsString);

        OneToOneLazyJoiner<OrderProduct, Product> joiner = new OrderProductToProductLazyJoiner();
        Collection<OrderProduct> orderProducts = joiner.getJoin(copp, products);

        Object[] FILE_HEADER = {"order_id", "aisle_id"};
        String path = "/home/michael/Ukma5/elastic-apriori/src/test/resources/csv/result.csv";
        FileWriter fileWriter = null;
        CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator("\n");
        fileWriter = new FileWriter(path);
        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
        csvFilePrinter.printRecord(FILE_HEADER);
        for (OrderProduct op : orderProducts) {
            csvFilePrinter.printRecord(
                    Arrays.asList(op.getOrderId(), op.getProduct().getAisleId()));
        }
        fileWriter.flush();
	fileWriter.close();
        csvFilePrinter.close();
    }

}
