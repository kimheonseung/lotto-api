package com.devh.api.lotto.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

import com.devh.api.lotto.constant.LottoConstant;

/**
 * <pre>
 * Description :
 *     LottoResultStore Entity에 대응되는 DTO
 * ===============================================
 * Member fields :
 *     private String rowId
 *     private Integer rank
 *     private String method
 *     private Integer storeNumber
 *     private String storeName
 *     private String storeAddress
 *     private String storePhone
 *     private String storeMapId
 *     private Integer turn
 * ===============================================
 *
 * Author : HeonSeung Kim
 * Date   : 2021-03-09
 * </pre>
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LottoResultStoreDTO {
    private String rowId;
    private Integer rank;
    @Builder.Default
    private String method = "";
    private Integer storeNumber;
    private String storeName;
    private String storeAddress;
    private String storeAddress1;
    private String storeAddress2;
    private String storeAddress3;
    @Builder.Default
    private Double storeLatitude = null;
    @Builder.Default
    private Double storeLongitude = null;
    private String storePhone;
    private String storeMapId;
    private Integer turn;

    public Map<String, Object> getMapForElasticsearch() {
        Map<String, Object> geoMap = null;
        if(this.storeLatitude != null && this.storeLongitude != null) {
            geoMap = new HashMap<>();
            geoMap.put("lat", this.storeLatitude);
            geoMap.put("lon", this.storeLongitude);
        }

        Map<String, Object> map = new HashMap<>();
        map.put(LottoConstant.LottoResultStoreKey.ROW_ID.getSnakeCase(), this.rowId);
        map.put(LottoConstant.LottoResultStoreKey.TURN.getSnakeCase(), this.turn);
        map.put(LottoConstant.LottoResultStoreKey.RANK.getSnakeCase(), this.rank);
        map.put(LottoConstant.LottoResultStoreKey.METHOD.getSnakeCase(), this.method);
        map.put(LottoConstant.LottoResultStoreKey.STORE_NUMBER.getSnakeCase(), this.storeNumber);
        map.put(LottoConstant.LottoResultStoreKey.STORE_NAME.getSnakeCase(), this.storeName);
        map.put(LottoConstant.LottoResultStoreKey.STORE_ADDRESS.getSnakeCase(), this.storeAddress);
        map.put(LottoConstant.LottoResultStoreKey.STORE_ADDRESS1.getSnakeCase(), this.storeAddress1);
        map.put(LottoConstant.LottoResultStoreKey.STORE_ADDRESS2.getSnakeCase(), this.storeAddress2);
        map.put(LottoConstant.LottoResultStoreKey.STORE_ADDRESS3.getSnakeCase(), this.storeAddress3);
        map.put(LottoConstant.LottoResultStoreKey.STORE_LOCATION.getSnakeCase(), geoMap);
        map.put(LottoConstant.LottoResultStoreKey.STORE_PHONE.getSnakeCase(), this.storePhone);
        map.put(LottoConstant.LottoResultStoreKey.STORE_MAP_ID.getSnakeCase(), this.storeMapId);
        return map;
    }
}
