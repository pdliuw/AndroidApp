package com.air.common.util;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author pd_liu on 2018/3/8.
 *         <p>
 *         资源关闭工具类
 *         </p>
 *         <p>
 *         Note:在使用OutputStream或则其他可关闭的对象之后，我们必须保证它最终被关闭了。
 *         例如：在finally块中调用 {@link #closeQuietly(Closeable)}
 *         </p>
 */

public final class CloseUtil {

    private CloseUtil() {
        throw new UnsupportedOperationException("非法的创建对象!");
    }

    /**
     * 关闭Closeable对象
     *
     * @param closeable 可关闭的对象
     */
    public static void closeQuietly(Closeable closeable) {

        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

}
