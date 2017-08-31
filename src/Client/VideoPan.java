package Client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.net.URI;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class VideoPan extends JPanel {

		
	private TitledBorder yoga = new TitledBorder("");
	private TitledBorder stretching = new TitledBorder("");
	private TitledBorder miley = new TitledBorder("");
	private TitledBorder dance = new TitledBorder("");
	private TitledBorder smi = new TitledBorder("");
//===========================================================	
	private ImageIcon imgE1 = new ImageIcon("KakaoTalk_20170820_180152201.jpg");
	private JLabel encourage = new JLabel(imgE1);
	private JButton renew = new JButton("갱신");
//===========================================================
	private JPanel healthPan = new JPanel();
	private JPanel yogaPan = new JPanel();
	private JPanel stretchPan = new JPanel();
	private JPanel mileyPan = new JPanel();
	private JPanel dancePan = new JPanel();
	private JPanel smiPan = new JPanel();
	
	private JScrollPane healthSc = new JScrollPane(healthPan);
	private JScrollPane yogaSc = new JScrollPane(yogaPan);
	private JScrollPane stretchSc = new JScrollPane(stretchPan);
	private JScrollPane mileySc = new JScrollPane(mileyPan);
	private JScrollPane danceSc = new JScrollPane(dancePan);
	private JScrollPane smiSc = new JScrollPane(smiPan);
//===========================================================	
	private Socket client;
	private DataInputStream dis;
	private DataOutputStream dos;
	private ObjectInputStream ois;
	private FileOutputStream fos;
	private BufferedOutputStream bos;
//===========================================================	
	private String[] urls;
	private String[] fileNames;
	private int fileSize = 0;
	private String path = "C:/Users/Administrator/4weeksWorkout/";
	
	private JButton[] b;
	private ImageIcon[] ic;
	
	public void insertImage() {
		for(int i=0;i<30;i++) {
			ic[i] = new ImageIcon(path+fileNames[i]);
			b[i].setIcon(ic[i]);
		}
	}
	
	public void unmarsharlling() {
		try {
			ImageIcon image;
			String url = null;
			String fileName = null;
			int fileSize = 0;
			byte[] filecontents = null;	
			
			dos.writeUTF("비디오패널 갱신");
			
			ois = new ObjectInputStream(client.getInputStream());
			VideoFileList vfl = (VideoFileList) ois.readObject();
		
			System.out.println(vfl.getUrl());
			System.out.println(vfl.getFilename());
			System.out.println(vfl.getFileSize());
			System.out.println(vfl.getFilecontents());
		
			filecontents = vfl.getFilecontents();
			File f = new File("");
			fos = new FileOutputStream(f);
			bos = new BufferedOutputStream(fos);
			dos = new DataOutputStream(bos);
			dos.write(filecontents);
			dos.flush();
			
			System.out.println("비디오패널 언마셜링 성공");
		
			for(int i=0;i<30;i++) {
				fileNames[i] = vfl.getFilename();
				urls[i] = vfl.getUrl();
			}//배열에 데이터 넣기완료
			
			System.out.println("배열에 데이터 넣기 완료");
			
			dos.close();
		}catch(Exception e) {
			System.out.println("비디오패널 언마셜링 실패");
		}
	}
	
	
	
	public void compInit() {

		setLayout(new GridLayout(8, 1));

		healthPan.setBackground(Color.white);
		yogaPan.setBackground(Color.white);
		stretchPan.setBackground(Color.white);
		mileyPan.setBackground(Color.white);
		dancePan.setBackground(Color.white);
		smiPan.setBackground(Color.white);
		
		b[0].setPreferredSize(new Dimension(200, 150));
		b[1].setPreferredSize(new Dimension(200, 150));
		b[2].setPreferredSize(new Dimension(200, 150));
		b[3].setPreferredSize(new Dimension(200, 150));
		b[4].setPreferredSize(new Dimension(200, 150));

		b[5].setPreferredSize(new Dimension(200, 150));
		b[6].setPreferredSize(new Dimension(200, 150));
		b[7].setPreferredSize(new Dimension(200, 150));
		b[8].setPreferredSize(new Dimension(200, 150));
		b[9].setPreferredSize(new Dimension(200, 150));

		b[10].setPreferredSize(new Dimension(200, 150));
		b[11].setPreferredSize(new Dimension(200, 150));
		b[12].setPreferredSize(new Dimension(200, 150));
		b[13].setPreferredSize(new Dimension(200, 150));
		b[14].setPreferredSize(new Dimension(200, 150));

		b[15].setPreferredSize(new Dimension(200, 150));
		b[16].setPreferredSize(new Dimension(200, 150));
		b[17].setPreferredSize(new Dimension(200, 150));
		b[18].setPreferredSize(new Dimension(200, 150));
		b[19].setPreferredSize(new Dimension(200, 150));
		
		b[20].setPreferredSize(new Dimension(200, 150));
		b[21].setPreferredSize(new Dimension(200, 150));
		b[22].setPreferredSize(new Dimension(200, 150));
		b[23].setPreferredSize(new Dimension(200, 150));
		b[24].setPreferredSize(new Dimension(200, 150));
		
		renew.setPreferredSize(new Dimension(100,70));
		

		add(encourage);
		
		yogaPan.setBorder(yoga);
		yogaPan.add(b[0]);
		yogaPan.add(b[1]);
		yogaPan.add(b[2]);
		yogaPan.add(b[3]);
		yogaPan.add(b[4]);
		add(yogaSc);

		stretchPan.setBorder(stretching);
		stretchPan.add(b[5]);
		stretchPan.add(b[6]);
		stretchPan.add(b[7]);
		stretchPan.add(b[8]);
		stretchPan.add(b[9]);
		add(stretchSc);

		mileyPan.setBorder(miley);
		mileyPan.add(b[10]);
		mileyPan.add(b[11]);
		mileyPan.add(b[12]);
		mileyPan.add(b[13]);
		mileyPan.add(b[14]);
		add(mileySc);

		dancePan.setBorder(dance);
		dancePan.add(b[15]);
		dancePan.add(b[16]);
		dancePan.add(b[17]);
		dancePan.add(b[18]);
		dancePan.add(b[19]);
		add(danceSc);
		
		smiPan.setBorder(smi);
		smiPan.add(b[20]);
		smiPan.add(b[21]);
		smiPan.add(b[22]);
		smiPan.add(b[23]);
		smiPan.add(b[24]);
		add(smiSc);
		
		add(renew,BorderLayout.CENTER);
	}
	
	public void eventInitYoga() {
		
		renew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				unmarsharlling();
				insertImage();
			}
		});
		
		b[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Desktop d = Desktop.getDesktop();
						d.browse(new URI(urls[0]));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});b[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[1]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
	});
		b[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Desktop d = Desktop.getDesktop();
						d.browse(new URI(urls[2]));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
		b[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Desktop d = Desktop.getDesktop();
						d.browse(new URI(urls[3]));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
		b[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Desktop d = Desktop.getDesktop();
						d.browse(new URI(urls[4]));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
	}//end
	
	public void eventInitStretching() {
		b[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[5]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[6]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[7]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[8]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b[9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[9]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void eventInitMiley() {
		b[10].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[10]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		b[11].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[11]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		b[12].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[12]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		b[13].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[13]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		b[14].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[14]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
	}//end

	public void eventInitDance() {
		b[15].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[15]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b[16].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[16]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b[17].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[17]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b[18].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[18]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		b[19].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI(urls[19]));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void eventInitSmi() {

		b[20].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI(urls[20]));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}
		});
		b[21].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI(urls[21]));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}
		});
		b[22].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI(urls[22]));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}
		});
		b[23].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI(urls[23]));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}
		});
		b[24].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI(urls[24]));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
			}
		});
	}
	
	public VideoPan(Socket client,DataInputStream dis,DataOutputStream dos) {
		this.client = client;
		this.dis = dis;
		this.dos = dos;
	}
	
	public VideoPan() {
		this.setBackground(Color.white);
		//unmarsharlling();
		//insertImage();
		compInit();
		eventInitYoga();
		eventInitMiley();
		eventInitDance();

	}

}
