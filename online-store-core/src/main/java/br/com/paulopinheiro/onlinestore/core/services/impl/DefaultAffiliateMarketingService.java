package br.com.paulopinheiro.onlinestore.core.services.impl;

import br.com.paulopinheiro.onlinestore.core.services.AffiliateMarketingService;
import br.com.paulopinheiro.onlinestore.persistence.dao.UserDao;
import br.com.paulopinheiro.onlinestore.persistence.dao.impl.JpaUserDao;
import java.util.Random;

public class DefaultAffiliateMarketingService implements AffiliateMarketingService {
    private static final int MAX_CHARS_IN_PARTNER_CODE = 6;

    private final UserDao userDao = new JpaUserDao();

    @Override
    public String generateUniquePartnerCode() {
        StringBuilder sb = new StringBuilder();
        char[] charactersForPartnerCode = {'A', 'B', 'C', 'D', 'E', 'F', 'G',
            'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U',
            'V', 'W', 'X', 'Y', 'Z', '0', '1',
            '2', '3', '4', '5', '6', '7', '8',
            '9'};
        Random r = new Random();
        for (int i = 0; i < MAX_CHARS_IN_PARTNER_CODE; i++) {
            sb.append(charactersForPartnerCode[r.nextInt(charactersForPartnerCode.length)]);
        }

        if (userDao.getUserByPartnerCode(sb.toString()) != null) {
            return this.generateUniquePartnerCode();
        } else {
            return sb.toString();
        }
    }
}
