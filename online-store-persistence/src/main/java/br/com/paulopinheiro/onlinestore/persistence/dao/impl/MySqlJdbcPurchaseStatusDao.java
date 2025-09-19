package br.com.paulopinheiro.onlinestore.persistence.dao.impl;

import br.com.paulopinheiro.onlinestore.persistence.dao.PurchaseStatusDao;
import br.com.paulopinheiro.onlinestore.persistence.dto.PurchaseStatusDto;
import br.com.paulopinheiro.onlinestore.persistence.utils.DBUtils;
import java.sql.SQLException;

public class MySqlJdbcPurchaseStatusDao implements PurchaseStatusDao {
    @Override
    public PurchaseStatusDto getPurchaseStatusById(Integer id) {
        try (var conn = DBUtils.getConnection(); var ps = conn.prepareStatement("SELECT * FROM purchase_status WHERE id = ?")) {

            ps.setInt(1, id);
            try (var rs = ps.executeQuery()) {
                if (rs.next()) {
                    var purchaseStatusDto = new PurchaseStatusDto();
                    purchaseStatusDto.setId(rs.getInt("id"));
                    purchaseStatusDto.setStatusName(rs.getString("status_name"));
                    return purchaseStatusDto;
                }
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
