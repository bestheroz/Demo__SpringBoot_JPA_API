package com.github.bestheroz.demo.api.internal.download;

import com.github.bestheroz.demo.entity.code.CodeRepository;
import com.github.bestheroz.demo.entity.member.MemberRepository;
import com.github.bestheroz.standard.common.file.excel.ExcelService;
import com.github.bestheroz.standard.common.file.excel.ExcelVO;
import com.github.bestheroz.standard.context.abstractview.AbstractExcelXView;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("api/excel")
@CrossOrigin(
    value = {"*"},
    exposedHeaders = {"Content-Disposition"})
@Slf4j
public class ExcelController {
  @Resource private MemberRepository memberRepository;
  @Resource private CodeRepository codeRepository;

  @GetMapping(value = "/members")
  public String excelAdminMembers(final Model model) {
    model.addAttribute(AbstractExcelXView.FILE_NAME, "Member_List");

    final List<ExcelVO> excelVOList = new ArrayList<>();
    AbstractExcelXView.addHeader(excelVOList, "사용자 아이디", "id", ExcelService.CellType.STRING);
    AbstractExcelXView.addHeader(excelVOList, "사용자 명", "name", ExcelService.CellType.STRING);
    AbstractExcelXView.addHeader(
        excelVOList,
        "권한",
        "authority",
        ExcelService.CellType.STRING_CENTER,
        this.codeRepository.getCodesByTypeOrderByDisplayOrder("AUTHORITY"));
    AbstractExcelXView.addHeader(excelVOList, "만료일", "expired", ExcelService.CellType.DATE);
    AbstractExcelXView.addHeader(excelVOList, "사용 가능", "available", ExcelService.CellType.STRING);
    AbstractExcelXView.addHeader(excelVOList, "작업 일시", "updated", ExcelService.CellType.DATE);
    AbstractExcelXView.addHeader(
        excelVOList,
        "작업자",
        "updatedBy",
        ExcelService.CellType.STRING,
        this.codeRepository.getMembers());
    model.addAttribute(AbstractExcelXView.EXCEL_VOS, excelVOList);
    model.addAttribute(
        AbstractExcelXView.LIST_DATA,
        this.memberRepository.findAll(Sort.by(Sort.DEFAULT_DIRECTION, "created")));
    return ExcelService.VIEW_NAME;
  }
}
