package com.mysiteforme.admin.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.Maps;
import com.mysiteforme.admin.dao.TableDao;
import com.mysiteforme.admin.entity.Dict;
import com.mysiteforme.admin.entity.VO.TableField;
import com.mysiteforme.admin.entity.VO.TableVO;
import com.mysiteforme.admin.service.DictService;
import com.mysiteforme.admin.service.TableService;
import com.mysiteforme.admin.util.Underline2Camel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huangyl on 2017/12/25.
 * todo:
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TableServiceImpl implements TableService {

    @Autowired
    private TableDao tableDao;

    @Autowired
    private DictService dictService;

    @Override
    public List<TableVO> listAll() {
        return tableDao.listAll();
    }

    @Override
    public Integer getTableCount() {
        return tableDao.selectTableCount();
    }

    @Override
    public Integer existTable(String tableName) {
        return tableDao.existTable(tableName);
    }

    @Override
    public Integer existTableField(Map<String, Object> map) {
        return tableDao.existTableField(map);
    }

    @Override
    public void creatTable(TableVO tableVO) {
        StringBuilder stringBuilder = new StringBuilder();
        //处理字段备注
        for (TableField field : tableVO.getFieldList()) {
            if ("timer".equals(field.getDofor()) || "editor".equals(field.getDofor()) || field.getDofor().contains("upload") || "switch".equals(field.getDofor())) {
                String fieldName = null;
                stringBuilder.append(field.getDofor() + "-" + getFieldName(field.getName(), field.getType()) + "-" + field.getIsNullValue());
                if ("switch".equals(field.getDofor())) {
                    stringBuilder.append("-").append(field.getDefaultValue()).append("-").append(field.getName()).append(",");
                } else {
                    stringBuilder.append(",");
                }
            }

            if ("select".equals(field.getDofor()) || "radio".equals(field.getDofor()) || "checkbox".equals(field.getDofor()) || "switch".equals(field.getDofor())) {
                StringBuilder desc = new StringBuilder();
                desc.append(tableVO.getComment()).append("-").append(field.getComment());
                if (field.getDictlist() != null && field.getDictlist().size() > 0) {
                    int i = 0;
                    for (Dict dict : field.getDictlist()) {
                        StringBuilder my = new StringBuilder();
                        my.append(desc);
                        dict.setDescription(my.append("(此数据为系统自动创建:数据表【" + tableVO.getName() + "】中的字段【" + field.getName() + "】对应的值)").toString());
                        dict.setSort(i);
                        i = i + 1;
                    }
                    dictService.saveDictList(tableVO.getName() + "_" + field.getName(), field.getDictlist());
                }
            } else {
                field.setDefaultValue(false);
            }
            //处理comment
            addFiledCommentValue(field);

        }
        //处理表的备注
        tableVO.setComment(tableVO.getComment() + "," + tableVO.getTabletype() + (stringBuilder.length() > 0 ? "," + stringBuilder.substring(0, stringBuilder.length() - 1) : ""));
        String json = JSONObject.toJSONString(tableVO);
        Map<String, Object> map = JSON.parseObject(json, new TypeReference<HashMap<String, Object>>() {
        });
        tableDao.creatTable(map);
    }

    private String getFieldName(String name, String type) {
        if ("bit".equalsIgnoreCase(type) && name.indexOf("is_") == 0) {
            name = name.replaceFirst("is_", "");
            return Underline2Camel.underline2Camel(name, true);
        } else {
            return Underline2Camel.underline2Camel(name, true);
        }
    }

    /***
     * 添加字段comment的值
     * @param field
     */
    private void addFiledCommentValue(TableField field) {
        StringBuilder sv = new StringBuilder();
        sv.append(field.getComment()).append(",").append(field.getDofor()).append(",").append(field.getIsNullValue()).append(",")
                .append(field.getDefaultValue()).append(",").append(field.getListIsShow()).append(",").append(field.getListIsSearch());
        field.setComment(sv.toString());
    }

    private void addColumnToDict(TableField field) {
        if ("select".equals(field.getDofor()) || "radio".equals(field.getDofor()) || "checkbox".equals(field.getDofor()) || "switch".equals(field.getDofor())) {
            StringBuilder desc = new StringBuilder();
            desc.append(field.getTableComment()).append("-").append(field.getComment());
            if (field.getDictlist() != null && field.getDictlist().size() > 0) {
                int i = 0;
                for (Dict dict : field.getDictlist()) {
                    StringBuilder my = new StringBuilder();
                    my.append(desc);
                    dict.setDescription(my.append("(此数据为系统自动创建:数据表【" + field.getTableName() + "】中的字段【" + field.getName() + "】对应的值)").toString());
                    dict.setSort(i);
                    i = i + 1;
                }
                dictService.saveDictList(field.getTableName() + "_" + field.getName(), field.getDictlist());
            }
        } else {
            field.setDefaultValue(false);
        }

    }

    @Override
    public void addColumn(TableField tableField) {
        //添加字典
        addColumnToDict(tableField);
        addFiledCommentValue(tableField);
        String json = JSONObject.toJSONString(tableField);
        Map<String, Object> map = JSON.parseObject(json, new TypeReference<HashMap<String, Object>>() {
        });
        tableDao.addColumn(map);
        changeTableComment(tableField.getTableName(), tableField.getTableComment(), tableField.getTableType());
    }

    @Override
    public void updateColumn(TableField tableField) {
        dictService.deleteByType(tableField.getTableName() + "_" + tableField.getOldName());
        if ("select".equals(tableField.getDofor()) || "radio".equals(tableField.getDofor()) || "checkbox".equals(tableField.getDofor()) || "switch".equals(tableField.getDofor())) {
            StringBuilder desc = new StringBuilder();
            desc.append(tableField.getTableComment()).append("-").append(tableField.getComment());
            if (tableField.getDictlist() != null && tableField.getDictlist().size() > 0) {
                for (Dict dict : tableField.getDictlist()) {
                    StringBuilder my = new StringBuilder();
                    my.append(desc).append("(此数据为系统自动创建:数据表【" + tableField.getTableName() + "】中的字段【" + tableField.getName() + "】对应的值)");
                    dict.setDescription(my.toString());
                    dictService.saveOrUpdateDict(dict);
                }
            }
        } else {
            tableField.setDefaultValue(false);
        }
        addFiledCommentValue(tableField);
        String json = JSONObject.toJSONString(tableField);
        Map<String, Object> map = JSON.parseObject(json, new TypeReference<HashMap<String, Object>>() {
        });
        if (tableField.getOldName().equals(tableField.getName())) {
            tableDao.updateColumnSameName(map);
        } else {
            tableDao.updateColumnDiffName(map);
        }
        changeTableComment(tableField.getTableName(), tableField.getTableComment(), tableField.getTableType());
    }

    @Override
    public void dropTableField(String fieldName, String tableName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("fieldName", fieldName);
        map.put("tableName", tableName);
        tableDao.dropTableField(map);
        dictService.deleteByType(tableName + "_" + fieldName);
    }


    @Override
    public void dropTable(String tableName) {
        tableDao.dropTable(tableName);
        dictService.deleteByTableName(tableName);
    }

    @Override
    public Page<TableVO> selectTablePage(Page<TableVO> objectPage, Map<String, Object> map) {
        List<TableVO> list = tableDao.listPage(map, objectPage);
        objectPage.setRecords(list);
        return objectPage;
    }

    @Override
    public List<TableField> selectFields(Map<String, Object> map) {
        return tableDao.selectFields(map);
    }

    @Override
    public Page<TableField> selectTableFieldPage(Page<TableField> objectPage, Map<String, Object> map) {
        List<TableField> list = tableDao.selectFields(objectPage, map);
        for (TableField t : list) {
            changeTableField(t);
        }
        objectPage.setRecords(list);
        return objectPage;
    }

    private void changeTableField(TableField t) {
        String[] c = t.getComment().split(",");
        t.setComment(c[0]);
        if (c.length > 1) {
            t.setDofor(c[1]);
        }
        if (c.length > 2) {
            t.setIsNullValue(c[2]);
        }
        if (c.length > 3) {
            t.setDefaultValue(Boolean.valueOf(c[3]));
        }
    }

    @Override
    public TableVO detailTable(String name) {
        return tableDao.selectDetailTable(name);
    }

    @Override
    public void changeTableName(String name, String oldname, String comment, Integer tableType) {
        Map<String, Object> tablemap = Maps.newHashMap();
        tablemap.put("name", oldname);
        tablemap.put("tableType", tableType);
        List<TableField> tableFields = selectFields(tablemap);
        for (TableField field : tableFields) {
            String fieldCommon = field.getComment();
            String[] c = fieldCommon.split(",");
            if (c.length > 1) {
                if ("select".equals(c[1]) || "radio".equals(c[1]) || "checkbox".equals(c[1]) || "switch".equals(c[1])) {
                    if (field.getDictlist() != null && field.getDictlist().size() > 0) {
                        int i = 0;
                        for (Dict dict : field.getDictlist()) {
                            StringBuilder my = new StringBuilder();
                            my.append(comment + "-" + name);
                            dict.setDescription(my.append("(此数据为系统自动创建:数据表【" + name + "】中的字段【" + field.getName() + "】对应的值)").toString());
                            dict.setType(name + "_" + field.getName());
                            i = i + 1;
                            dictService.saveOrUpdateDict(dict);
                        }
                    }
                }
            }
        }

        Map<String, Object> map = Maps.newHashMap();
        map.put("name", name);
        map.put("oldname", oldname);
        tableDao.changeTableName(map);
    }

    @Override
    public void changeTableComment(String name, String comment, Integer tableType) {
        Map<String, Object> tablemap = Maps.newHashMap();
        tablemap.put("name", name);
        tablemap.put("tableType", tableType);
        List<TableField> list = selectFields(tablemap);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(comment).append(",").append(tableType).append(",");
        //处理字段备注
        for (TableField field : list) {
            String[] c = field.getComment().split(",");
            if (c.length > 1) {
                if ("timer".equals(c[1]) || "editor".equals(c[1]) || c[1].contains("upload") || "switch".equals(c[1])) {
                    stringBuilder.append(c[1] + "-" + getFieldName(field.getName(), field.getType()) + "-" + field.getIsNullValue());
                    if ("switch".equals(c[1])) {
                        stringBuilder.append("-").append(c[3]).append("-").append(field.getName()).append(",");
                    } else {
                        stringBuilder.append(",");
                    }
                }
            }
        }
        String str = stringBuilder.substring(0, stringBuilder.length() - 1);
        Map<String, Object> map = Maps.newHashMap();
        map.put("tableName", name);
        map.put("comment", str);
        tableDao.changeTableComment(map);
    }
}
