package id.ac.tazkia.payment.virtualaccount.service;

import id.ac.tazkia.payment.virtualaccount.dao.BankDao;
import id.ac.tazkia.payment.virtualaccount.dao.TagihanDao;
import id.ac.tazkia.payment.virtualaccount.dao.VirtualAccountDao;
import id.ac.tazkia.payment.virtualaccount.entity.Bank;
import id.ac.tazkia.payment.virtualaccount.entity.Tagihan;
import id.ac.tazkia.payment.virtualaccount.entity.VirtualAccount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service @Transactional
public class TagihanService {

    @Autowired private TagihanDao tagihanDao;
    @Autowired private VirtualAccountDao virtualAccountDao;
    @Autowired private BankDao bankDao;

    public void createTagihan(Tagihan t) {
        tagihanDao.save(t);
        for (Bank b : bankDao.findAll()) {
            if (b.getAktif()) {
                VirtualAccount va = new VirtualAccount();
                va.setBank(b);
                va.setTagihan(t);
                virtualAccountDao.save(va);
            }
        }

    }
}
