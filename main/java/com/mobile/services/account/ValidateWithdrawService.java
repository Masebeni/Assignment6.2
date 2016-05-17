package com.mobile.services.account;

import com.mobile.domain.account.Credit;

/**
 * Created by Isiphile on 2016-05-12.
 */
public interface ValidateWithdrawService {

    boolean isValidWithdraw(Credit account, double withdrawRequest);
}
