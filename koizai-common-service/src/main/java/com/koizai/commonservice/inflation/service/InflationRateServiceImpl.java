package com.koizai.commonservice.inflation.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import com.koizai.commonservice.inflation.model.InflationRate;
import com.koizai.commonservice.inflation.model.InflationRateEnum;

import lombok.extern.slf4j.Slf4j;

/**
 * An interface for infalation rate service
 *
 * @author rajukc@koizai.com
 */
@Service
@Slf4j
public class InflationRateServiceImpl implements InflationRateService {

    private List<InflationRate> readyList = new ArrayList<>();

    @Autowired
    private ResourceLoader resourceLoader;

    @Override
    public InflationRate findByCountryCodeAndYear(String countryCode, int year) {
        return readyList.stream().filter(item -> item.getCountryCode().equals(countryCode) && item.getYear() == year).findFirst().get();
    }

    @Override
    public List<InflationRate> findByCountryCode(String countryCode) {
        return readyList.stream().filter(item -> item.getCountryCode().equals(countryCode)).sorted(Comparator.comparingInt(InflationRate::getYear))
                .collect(Collectors.toList());
    }

    @Override
    public void processFile() throws Exception {
        log.info("Starting Inflation Rate Processing.....");
        Resource resource = resourceLoader.getResource("classpath:Inflation-data.xlsx");
        Workbook wb = WorkbookFactory.create(resource.getInputStream());
        XSSFSheet clientSheet = (XSSFSheet) wb.getSheet("hcpi_a");
        final int START_ROW = 1;
        int rowNo = START_ROW;
        short start = -1;
        short end = -1;
        XSSFRow firstRow = clientSheet.getRow(0);
        for (int i = 0; i < firstRow.getLastCellNum(); i++) {
            if (org.springframework.util.ObjectUtils.isEmpty(firstRow.getCell(i))) {
                end = (short) i;
                break;
            }

            if (firstRow.getCell(i).toString().contains("2015"))
                start = (short) i;

        }

        while (true) {
            XSSFRow row = clientSheet.getRow(rowNo);
            if (row == null) {
                break;
            }

            XSSFCell firstCell = row.getCell(0);
            if (firstCell == null) {
                break;
            }

            int cnt = 0;
            for (int i = start; i < end; i++) {
                XSSFCell cellYear = firstRow.getCell(i);
                InflationRate dto = new InflationRate();
                for (int j = 0; j < InflationRateEnum.values().length - 1; j++) {
                    InflationRateEnum col = InflationRateEnum.values()[j];
                    XSSFCell cell;
                    if (col == InflationRateEnum.COUNTRY_CODE) {
                        cell = row.getCell(j);
                        dto.setCountryCode(cell.toString());
                    } else if (col == InflationRateEnum.COUNTRY) {
                        cell = row.getCell(j);
                        dto.setCountry(cell.toString());
                    } else if (col == InflationRateEnum.IMF_COUNTRY_CODE) {
                        cell = row.getCell(j);
                        dto.setImfCountryCode(Double.valueOf(cell.toString()).intValue());
                    } else if (col == InflationRateEnum.YEAR) {
                        cell = row.getCell(cnt + start);
                        dto.setYear(Double.valueOf(cellYear.toString()).intValue());
                        if (org.springframework.util.ObjectUtils.isEmpty(cell)) {
                            dto.setInflationRate(BigDecimal.ZERO.setScale(2));
                        } else {
                            dto.setInflationRate(new BigDecimal(cell.getNumericCellValue()).setScale(2, RoundingMode.CEILING));
                        }
                    }

                }
                readyList.add(dto);
                cnt++;

            }

            rowNo++;
        }
        log.info("Ending Inflation Rate Processing with data -" + readyList.size());
    }
}