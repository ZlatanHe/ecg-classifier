package com.apple.ecg.matlabcontrol;

import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;

public class MatlabControlDemo {

    public void demo() throws Exception {
        //Create a proxy, which we will use to control MATLAB
        MatlabProxyFactory factory = new MatlabProxyFactory();
        MatlabProxy proxy = factory.getProxy();

        //Display 'hello world' just like when using the demo
        proxy.eval("disp('hello world')");

        //Disconnect the proxy from MATLAB
        proxy.disconnect();
    }
}
