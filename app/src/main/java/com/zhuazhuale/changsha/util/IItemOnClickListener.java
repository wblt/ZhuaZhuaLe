
/*
 * *********************************************************************
 *  * Created by mac on 2017.
 *  *
 *  * 湖南长沙阳环科技实业有限公司.
 *  * @Copyright (c) 2003-2017 yhcloud, Inc. All rights reserved.
 *  *
 *  *	This copy of Ice is licensed to you under the terms described in the
 *  *	ICE_LICENSE file included in this distribution.
 *  *
 *  *	@license http://www.yhcloud.com.cn/license/
 *  *********************************************************************
 */

package com.zhuazhuale.changsha.util;

import android.view.View;

/**
 * Created by leig on 2017/2/24.
 */

public interface
IItemOnClickListener {

    void itemOnClick(View view, int position);

    void itemLongOnClick(View view, int position);

}
