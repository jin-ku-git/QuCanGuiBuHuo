package com.sz.jjj.baselibrary.utils;

/**
 * Created by IntelliJ IDEA
 * User:soochowdavid
 * Date:2014/12/11
 * Time:9:28
 * To change this template use File | Settings | File Templates.
 * <p>
 * Copyright (C) 2009-2010 Yichuan, Fuchun All rights reserved.
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * @(#) IdcardUtils.java Date: 2010-06-17
 * <p>
 * Copyright (C) 2009-2010 Yichuan, Fuchun All rights reserved.
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @(#) IdcardUtils.java Date: 2010-06-17
 * <p>
 * Copyright (C) 2009-2010 Yichuan, Fuchun All rights reserved.
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @(#) IdcardUtils.java Date: 2010-06-17
 * <p>
 * Copyright (C) 2009-2010 Yichuan, Fuchun All rights reserved.
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @(#) IdcardUtils.java Date: 2010-06-17
 * <p>
 * Copyright (C) 2009-2010 Yichuan, Fuchun All rights reserved.
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @(#) IdcardUtils.java Date: 2010-06-17
 * <p>
 * Copyright (C) 2009-2010 Yichuan, Fuchun All rights reserved.
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @(#) IdcardUtils.java Date: 2010-06-17
 */
/**
 * Copyright (C) 2009-2010 Yichuan, Fuchun All rights reserved.
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements. See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * @(#) IdcardUtils.java Date: 2010-06-17
 */


import android.text.TextUtils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * ??????????????????
 *
 * @author June
 * @version 1.0, 2010-06-17
 */
public class IdcardTool {

    /**
     * ??????????????????????????????????????????
     */
    public static final int CHINA_ID_MIN_LENGTH = 15;

    /**
     * ??????????????????????????????????????????
     */
    public static final int CHINA_ID_MAX_LENGTH = 18;

    /**
     * ????????????????????????
     */
    public static final String cityCode[] = {
            "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41",
            "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71",
            "81", "82", "91"
    };

    /**
     * ??????????????????
     */
    public static final int power[] = {
            7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2
    };

    /**
     * ???18????????????
     */
    public static final String verifyCode[] = {
            "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"
    };
    /**
     * ????????????
     */
    public static final int MIN = 1930;
    public static Map<String, String> cityCodes = new HashMap<String, String>();
    /**
     * ?????????????????????????????????
     */
    public static Map<String, Integer> twFirstCode = new HashMap<String, Integer>();
    /**
     * ?????????????????????????????????
     */
    public static Map<String, Integer> hkFirstCode = new HashMap<String, Integer>();

    static {
        cityCodes.put("11", "??????");
        cityCodes.put("12", "??????");
        cityCodes.put("13", "??????");
        cityCodes.put("14", "??????");
        cityCodes.put("15", "?????????");
        cityCodes.put("21", "??????");
        cityCodes.put("22", "??????");
        cityCodes.put("23", "?????????");
        cityCodes.put("31", "??????");
        cityCodes.put("32", "??????");
        cityCodes.put("33", "??????");
        cityCodes.put("34", "??????");
        cityCodes.put("35", "??????");
        cityCodes.put("36", "??????");
        cityCodes.put("37", "??????");
        cityCodes.put("41", "??????");
        cityCodes.put("42", "??????");
        cityCodes.put("43", "??????");
        cityCodes.put("44", "??????");
        cityCodes.put("45", "??????");
        cityCodes.put("46", "??????");
        cityCodes.put("50", "??????");
        cityCodes.put("51", "??????");
        cityCodes.put("52", "??????");
        cityCodes.put("53", "??????");
        cityCodes.put("54", "??????");
        cityCodes.put("61", "??????");
        cityCodes.put("62", "??????");
        cityCodes.put("63", "??????");
        cityCodes.put("64", "??????");
        cityCodes.put("65", "??????");
        cityCodes.put("71", "??????");
        cityCodes.put("81", "??????");
        cityCodes.put("82", "??????");
        cityCodes.put("91", "??????");
        twFirstCode.put("A", 10);
        twFirstCode.put("B", 11);
        twFirstCode.put("C", 12);
        twFirstCode.put("D", 13);
        twFirstCode.put("E", 14);
        twFirstCode.put("F", 15);
        twFirstCode.put("G", 16);
        twFirstCode.put("H", 17);
        twFirstCode.put("J", 18);
        twFirstCode.put("K", 19);
        twFirstCode.put("L", 20);
        twFirstCode.put("M", 21);
        twFirstCode.put("N", 22);
        twFirstCode.put("P", 23);
        twFirstCode.put("Q", 24);
        twFirstCode.put("R", 25);
        twFirstCode.put("S", 26);
        twFirstCode.put("T", 27);
        twFirstCode.put("U", 28);
        twFirstCode.put("V", 29);
        twFirstCode.put("X", 30);
        twFirstCode.put("Y", 31);
        twFirstCode.put("W", 32);
        twFirstCode.put("Z", 33);
        twFirstCode.put("I", 34);
        twFirstCode.put("O", 35);
        hkFirstCode.put("A", 1);
        hkFirstCode.put("B", 2);
        hkFirstCode.put("C", 3);
        hkFirstCode.put("R", 18);
        hkFirstCode.put("U", 21);
        hkFirstCode.put("Z", 26);
        hkFirstCode.put("X", 24);
        hkFirstCode.put("W", 23);
        hkFirstCode.put("O", 15);
        hkFirstCode.put("N", 14);
    }

    /**
     * ???????????????????????????
     */
    public static boolean validateCard(String idCard) {
        if (TextUtils.isEmpty(idCard)) {
            return false;
        }
        String card = fixPersonIDCode(idCard.trim());
        if (validateIdCard18(card)) {
            return true;
        }
        return false;
    }

    /**
     * ??????18???????????????????????????
     *
     * @param idCard ????????????
     * @return ????????????
     */
    public static boolean validateIdCard18(String idCard) {
        boolean bTrue = false;
        if (idCard.length() == CHINA_ID_MAX_LENGTH) {
            // ???17???
            String code17 = idCard.substring(0, 17);
            // ???18???
            String code18 = idCard.substring(17, CHINA_ID_MAX_LENGTH);
            if (isNum(code17)) {
                char[] cArr = code17.toCharArray();
                if (cArr != null) {
                    int[] iCard = converCharToInt(cArr);
                    int iSum17 = getPowerSum(iCard);
                    // ???????????????
                    String val = getCheckCode18(iSum17);
                    if (val.length() > 0) {
                        if (val.equalsIgnoreCase(code18) && checkIdcardTime(idCard)) {
                            bTrue = true;
                        }
                    }
                }
            }
        }
        return bTrue;
    }

    /**
     * ????????????????????????
     * @param idCard
     * @return
     */
    public static boolean checkIdcardTime(String idCard) {
        String birth = getBirthByIdCard(idCard);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        dateFormat.setLenient(false);
        ParsePosition pos = new ParsePosition(0);
        Date result = dateFormat.parse(birth, pos);
        if (pos.getIndex() == 0) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * ??????10???????????????????????????
     *
     * @param idCard ????????????
     * @return ?????????????????????
     * <p>
     * [0] - ???????????????????????? [1] - ??????(???M,???F,??????N) [2] - ????????????(??????true,?????????false)
     * ????????????????????????????????????null
     * </p>
     */
    public static String[] validateIdCard10(String idCard) {
        String[] info = new String[3];
        String card = idCard.replaceAll("[\\(|\\)]", "");
        if (card.length() != 8 && card.length() != 9 && idCard.length() != 10) {
            return null;
        }
        if (idCard.matches("^[a-zA-Z][0-9]{9}$")) { // ??????
            info[0] = "??????";
            System.out.println("11111");
            String char2 = idCard.substring(1, 2);
            if (char2.equals("1")) {
                info[1] = "M";
                System.out.println("MMMMMMM");
            } else if (char2.equals("2")) {
                info[1] = "F";
                System.out.println("FFFFFFF");
            } else {
                info[1] = "N";
                info[2] = "false";
                System.out.println("NNNN");
                return info;
            }
            info[2] = validateTWCard(idCard) ? "true" : "false";
        } else if (idCard.matches("^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$")) { // ??????
            info[0] = "??????";
            info[1] = "N";
            // TODO
        } else if (idCard.matches("^[A-Z]{1,2}[0-9]{6}\\(?[0-9A]\\)?$")) { // ??????
            info[0] = "??????";
            info[1] = "N";
            info[2] = validateHKCard(idCard) ? "true" : "false";
        } else {
            return null;
        }
        return info;
    }

    /**
     * 15??????18???
     * @param personIDCode
     * @return
     */
    public static String fixPersonIDCode(String personIDCode) {
        if (personIDCode == null || "".equals(personIDCode) || personIDCode.trim().length() != 15) {
            return personIDCode;
        }
        String id17 = personIDCode.substring(0, 6) + "19"
                + personIDCode.substring(6, 15); // 15???????????????\'19\'
        char[] code = {'1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2'}; // 11???
        int[] factor = {0, 2, 4, 8, 5, 10, 9, 7, 3, 6, 1, 2, 4, 8, 5, 10, 9, 7}; // 18???;
        int[] idcd = new int[18];
        int i;
        int j;
        int sum;
        int remainder;
        for (i = 1; i < 18; i++) {
            j = 17 - i;
            idcd[i] = Integer.parseInt(id17.substring(j, j + 1));
        }
        sum = 0;
        for (i = 1; i < 18; i++) {
            sum = sum + idcd[i] * factor[i];
        }
        remainder = sum % 11;
        String lastCheckBit = String.valueOf(code[remainder]);
        return id17 + lastCheckBit;
    }


    /**
     * ???????????????????????????
     *
     * @param idCard ???????????????
     * @return ?????????????????????
     */
    public static boolean validateTWCard(String idCard) {
        String start = idCard.substring(0, 1);
        String mid = idCard.substring(1, 9);
        String end = idCard.substring(9, 10);
        Integer iStart = twFirstCode.get(start);
        Integer sum = iStart / 10 + (iStart % 10) * 9;
        char[] chars = mid.toCharArray();
        Integer iflag = 8;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iflag;
            iflag--;
        }
        return (sum % 10 == 0 ? 0 : (10 - sum % 10)) == Integer.valueOf(end) ? true : false;
    }

    /**
     * ???????????????????????????(??????Bug????????????????????????????????????)
     * <p>
     * ????????????2????????????????????????????????????????????????????????????????????????????????????????????????58 ???2???????????????A-Z??????????????????10-35
     * ????????????????????????0-9?????????????????????"A"???"A"??????10
     * </p>
     * <p>
     * ?????????????????????????????????????????????????????????9-1????????????????????????11?????????????????????
     * </p>
     *
     * @param idCard ???????????????
     * @return ?????????????????????
     */
    public static boolean validateHKCard(String idCard) {
        String card = idCard.replaceAll("[\\(|\\)]", "");
        Integer sum = 0;
        if (card.length() == 9) {
            sum = (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 9
                    + (Integer.valueOf(card.substring(1, 2).toUpperCase().toCharArray()[0]) - 55) * 8;
            card = card.substring(1, 9);
        } else {
            sum = 522 + (Integer.valueOf(card.substring(0, 1).toUpperCase().toCharArray()[0]) - 55) * 8;
        }
        String mid = card.substring(1, 7);
        String end = card.substring(7, 8);
        char[] chars = mid.toCharArray();
        Integer iflag = 7;
        for (char c : chars) {
            sum = sum + Integer.valueOf(c + "") * iflag;
            iflag--;
        }
        if (end.toUpperCase().equals("A")) {
            sum = sum + 10;
        } else {
            sum = sum + Integer.valueOf(end);
        }
        return (sum % 11 == 0) ? true : false;
    }

    /**
     * ????????????????????????????????????
     *
     * @param ca ????????????
     * @return ????????????
     */
    public static int[] converCharToInt(char[] ca) {
        int len = ca.length;
        int[] iArr = new int[len];
        try {
            for (int i = 0; i < len; i++) {
                iArr[i] = Integer.parseInt(String.valueOf(ca[i]));
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return iArr;
    }

    /**
     * ??????????????????????????????????????????????????????????????????????????????
     *
     * @param iArr
     * @return ??????????????????
     */
    public static int getPowerSum(int[] iArr) {
        int iSum = 0;
        if (power.length == iArr.length) {
            for (int i = 0; i < iArr.length; i++) {
                for (int j = 0; j < power.length; j++) {
                    if (i == j) {
                        iSum = iSum + iArr[i] * power[j];
                    }
                }
            }
        }
        return iSum;
    }

    /**
     * ???power?????????11???????????????????????????????????????
     *
     * @param iSum
     * @return ?????????
     */
    public static String getCheckCode18(int iSum) {
        String sCode = "";
        switch (iSum % 11) {
            case 10:
                sCode = "2";
                break;
            case 9:
                sCode = "3";
                break;
            case 8:
                sCode = "4";
                break;
            case 7:
                sCode = "5";
                break;
            case 6:
                sCode = "6";
                break;
            case 5:
                sCode = "7";
                break;
            case 4:
                sCode = "8";
                break;
            case 3:
                sCode = "9";
                break;
            case 2:
                sCode = "x";
                break;
            case 1:
                sCode = "0";
                break;
            case 0:
                sCode = "1";
                break;
        }
        return sCode;
    }

    /**
     * ??????????????????????????????
     *
     * @param idCard ????????????
     * @return ??????
     */
    public static int getAgeByIdCard(String idCard) {
        int iAge = 0;
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = fixPersonIDCode(idCard);
        }
        String year = idCard.substring(6, 10);
        Calendar cal = Calendar.getInstance();
        int iCurrYear = cal.get(Calendar.YEAR);
        iAge = iCurrYear - Integer.valueOf(year);
        return iAge;
    }

    /**
     * ??????????????????????????????
     *
     * @param idCard ????????????
     * @return ??????(yyyyMMdd)
     */
    public static String getBirthByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = fixPersonIDCode(idCard);
        }
        return idCard.substring(6, 14);
    }

    /**
     * ?????????????????????????????????
     *
     * @param idCard ????????????
     * @return ??????(yyyy)
     */
    public static Short getYearByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = fixPersonIDCode(idCard);
        }
        return Short.valueOf(idCard.substring(6, 10));
    }

    /**
     * ?????????????????????????????????
     *
     * @param idCard ????????????
     * @return ??????(MM)
     */
    public static Short getMonthByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = fixPersonIDCode(idCard);
        }
        return Short.valueOf(idCard.substring(10, 12));
    }

    /**
     * ?????????????????????????????????
     *
     * @param idCard ????????????
     * @return ??????(dd)
     */
    public static Short getDateByIdCard(String idCard) {
        Integer len = idCard.length();
        if (len < CHINA_ID_MIN_LENGTH) {
            return null;
        } else if (len == CHINA_ID_MIN_LENGTH) {
            idCard = fixPersonIDCode(idCard);
        }
        return Short.valueOf(idCard.substring(12, 14));
    }

    /**
     * ??????????????????????????????
     *
     * @param idCard ????????????
     * @return ??????(M-??????F-??????N-??????)
     */
    public static String getGenderByIdCard(String idCard) {
        if (TextUtils.isEmpty(idCard)) {
            return idCard;
        }
        String sGender = "N";
        if (idCard.length() == CHINA_ID_MIN_LENGTH) {
            idCard = fixPersonIDCode(idCard);
        }
        String sCardNum = idCard.substring(16, 17);
        if (Integer.parseInt(sCardNum) % 2 != 0) {
            sGender = "M";
        } else {
            sGender = "F";
        }
        return sGender;
    }

    /**
     * ????????????????????????????????????
     *
     * @param idCard ????????????
     * @return ???????????????
     */
    public static String getProvinceByIdCard(String idCard) {
        int len = idCard.length();
        String sProvince = null;
        String sProvinNum = "";
        if (len == CHINA_ID_MIN_LENGTH || len == CHINA_ID_MAX_LENGTH) {
            sProvinNum = idCard.substring(0, 2);
        }
        sProvince = cityCodes.get(sProvinNum);
        return sProvince;
    }

    /**
     * ????????????
     *
     * @param val
     * @return ??????????????????
     */
    public static boolean isNum(String val) {
        return val == null || "".equals(val) ? false : val.matches("^[0-9]*$");
    }

    /**
     * ???????????????????????? ????????????
     *
     * @param iYear  ???????????????(???)
     * @param iMonth ???????????????(??? 1-12)
     * @param iDate  ???????????????(???)
     * @return ????????????
     */
    public static boolean valiDate(int iYear, int iMonth, int iDate) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int datePerMonth;
        if (iYear < MIN || iYear >= year) {
            return false;
        }
        if (iMonth < 1 || iMonth > 12) {
            return false;
        }
        switch (iMonth) {
            case 4:
            case 6:
            case 9:
            case 11:
                datePerMonth = 30;
                break;
            case 2:
                boolean dm = ((iYear % 4 == 0 && iYear % 100 != 0) || (iYear % 400 == 0))
                        && (iYear > MIN && iYear < year);
                datePerMonth = dm ? 29 : 28;
                break;
            default:
                datePerMonth = 31;
        }
        return (iDate >= 1) && (iDate <= datePerMonth);
    }
}