package org.balthie.demo.my.ios.message;


public class MainApnsSend
{
    public static void main(String[] args) throws Exception
    {
      /*  try
        {
            String deviceToken = "e775b5892f3334427c14def8aa4d8189a4ec1c795020072f4baa7ee92e50b1db";// iphone�ֻ���ȡ��token
            
            PayLoad payLoad = new PayLoad();
            payLoad.addAlert("�ҵ�push����");// push������
            payLoad.addBadge(1);// ͼ��С��Ȧ����ֵ
            payLoad.addSound("default");// ����
            
            PushNotificationManager pushManager = PushNotificationManager.getInstance();
            pushManager.addDevice("iPhone", deviceToken);
            
            // Connect to APNs
            *//************************************************
             * ���Եķ�������ַ��gateway.sandbox.push.apple.com /�˿�2195 ��Ʒ���ͷ�������ַ��gateway.push.apple.com /
             * 2195
             ***************************************************//*
            String host = "gateway.sandbox.push.apple.com";
            int port = 2195;
            String certificatePath = "/Users/jcjc/Desktop/push_p.p12";// ������֤��
            String certificatePassword = "sunlg";// �˴�ע�⵼����֤�����벻��Ϊ����Ϊ������ᱨ��
            pushManager.initializeConnection(host, port, certificatePath, certificatePassword,
                    SSLConnectionHelper.KEYSTORE_TYPE_PKCS12);
            
            // Send Push
            Device client = pushManager.getDevice("iPhone");
            pushManager.sendNotification(client, payLoad);
            pushManager.stopConnection();
            
            pushManager.removeDevice("iPhone");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }*/
        
    }
}