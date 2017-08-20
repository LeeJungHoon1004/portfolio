package Client;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

public class VideoPan extends JPanel {

	private TitledBorder yoga = new TitledBorder("요가");
	private TitledBorder stretching = new TitledBorder("스트레칭");
	private TitledBorder miley = new TitledBorder("마일리사이러스");
	private TitledBorder dance = new TitledBorder("춤 다이어트");
	private TitledBorder smi = new TitledBorder("스미홈트");
	
//===========================================================	
	private ImageIcon imgE1 = new ImageIcon("자극글귀.JPG");
	private JLabel encourage = new JLabel(imgE1);
//===========================================================
	private ImageIcon imgY1 = new ImageIcon("y1.JPG");
	private ImageIcon imgY2 = new ImageIcon("y2.JPG");
	private ImageIcon imgY3 = new ImageIcon("y3.JPG");
	private ImageIcon imgY4 = new ImageIcon("y4.JPG");
	private ImageIcon imgY5 = new ImageIcon("y5.JPG");
	private JButton videoY1 = new JButton(imgY1);
	private JButton videoY2 = new JButton(imgY2);
	private JButton videoY3 = new JButton(imgY3);
	private JButton videoY4 = new JButton(imgY4);
	private JButton videoY5 = new JButton(imgY5);
//===========================================================
	private ImageIcon imgs1 = new ImageIcon("s1.JPG");
	private ImageIcon imgs11 = new ImageIcon("s1.JPG");
	private ImageIcon imgs2 = new ImageIcon("s2.JPG");
	private ImageIcon imgs3 = new ImageIcon("s3.JPG");
	private ImageIcon imgs4 = new ImageIcon("s4.JPG");
	private ImageIcon imgs5 = new ImageIcon("s5.JPG");
	private JButton videoS1 = new JButton(imgs1);
	private JButton videoS11 = new JButton(imgs1);
	private JButton videoS2 = new JButton(imgs2);
	private JButton videoS3 = new JButton(imgs3);
	private JButton videoS4 = new JButton(imgs4);
	private JButton videoS5 = new JButton(imgs5);
//===========================================================
	private ImageIcon imgM1 = new ImageIcon("m1.JPG");
	private ImageIcon imgM2 = new ImageIcon("m2.JPG");
	private ImageIcon imgM3 = new ImageIcon("m3.JPG");
	private ImageIcon imgM4 = new ImageIcon("m4.JPG");
	private ImageIcon imgM5 = new ImageIcon("m5.JPG");
	private JButton miley1 = new JButton(imgM1);
	private JButton miley2 = new JButton(imgM2);
	private JButton miley3 = new JButton(imgM3);
	private JButton miley4 = new JButton(imgM4);
	private JButton miley5 = new JButton(imgM5);
//===========================================================
	private ImageIcon imgD1 = new ImageIcon("d1.jpg");
	private ImageIcon imgD2 = new ImageIcon("d2.JPG");
	private ImageIcon imgD3 = new ImageIcon("d3.JPG");
	private ImageIcon imgD4 = new ImageIcon("d4.JPG");
	private ImageIcon imgD5 = new ImageIcon("d5.JPG");
	private JButton dance1 = new JButton(imgD1);
	private JButton dance2 = new JButton(imgD2);
	private JButton dance3 = new JButton(imgD3);
	private JButton dance4 = new JButton(imgD4);
	private JButton dance5 = new JButton(imgD5);
//===========================================================
	private ImageIcon imgSmi1 = new ImageIcon("smi1.JPG");
	private ImageIcon imgSmi2 = new ImageIcon("smi2.JPG");
	private ImageIcon imgSmi3 = new ImageIcon("smi3.JPG");
	private ImageIcon imgSmi4 = new ImageIcon("smi4.JPG");
	private ImageIcon imgSmi5 = new ImageIcon("smi5.JPG");
	private JButton smi1 = new JButton(imgSmi1);
	private JButton smi2 = new JButton(imgSmi2);
	private JButton smi3 = new JButton(imgSmi3);
	private JButton smi4 = new JButton(imgSmi4);
	private JButton smi5 = new JButton(imgSmi5);
	private JButton moreSmi = new JButton("스미트홈 더보기..");
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
	
	public void compInit() {

		setLayout(new GridLayout(6, 1));
		
		
		
		videoY1.setPreferredSize(new Dimension(200, 150));
		videoY2.setPreferredSize(new Dimension(200, 150));
		videoY3.setPreferredSize(new Dimension(200, 150));
		videoY4.setPreferredSize(new Dimension(200, 150));
		videoY5.setPreferredSize(new Dimension(200, 150));

		videoS1.setPreferredSize(new Dimension(200, 150));
		videoS11.setPreferredSize(new Dimension(200, 150));
		videoS2.setPreferredSize(new Dimension(200, 150));
		videoS3.setPreferredSize(new Dimension(200, 150));
		videoS4.setPreferredSize(new Dimension(200, 150));
		videoS5.setPreferredSize(new Dimension(200, 150));

		miley1.setPreferredSize(new Dimension(200, 150));
		miley2.setPreferredSize(new Dimension(200, 150));
		miley3.setPreferredSize(new Dimension(200, 150));
		miley4.setPreferredSize(new Dimension(200, 150));
		miley5.setPreferredSize(new Dimension(200, 150));

		dance1.setPreferredSize(new Dimension(200, 150));
		dance2.setPreferredSize(new Dimension(200, 150));
		dance3.setPreferredSize(new Dimension(200, 150));
		dance4.setPreferredSize(new Dimension(200, 150));
		dance5.setPreferredSize(new Dimension(200, 150));
		
		smi1.setPreferredSize(new Dimension(200, 150));
		smi2.setPreferredSize(new Dimension(200, 150));
		smi3.setPreferredSize(new Dimension(200, 150));
		smi4.setPreferredSize(new Dimension(200, 150));
		smi5.setPreferredSize(new Dimension(200, 150));
		
		

		add(encourage);
		
		yogaPan.setBorder(yoga);
		yogaPan.add(videoY1);
		yogaPan.add(videoY2);
		yogaPan.add(videoY3);
		yogaPan.add(videoY4);
		yogaPan.add(videoY5);
		add(yogaSc);

		stretchPan.setBorder(stretching);
		stretchPan.add(videoS1);
		stretchPan.add(videoS11);
		stretchPan.add(videoS2);
		stretchPan.add(videoS3);
		stretchPan.add(videoS4);
		stretchPan.add(videoS5);
		add(stretchSc);

		mileyPan.setBorder(miley);
		mileyPan.add(miley1);
		mileyPan.add(miley2);
		mileyPan.add(miley3);
		mileyPan.add(miley4);
		mileyPan.add(miley5);
		add(mileySc);

		dancePan.setBorder(dance);
		dancePan.add(dance1);
		dancePan.add(dance2);
		dancePan.add(dance3);
		dancePan.add(dance4);
		dancePan.add(dance5);
		add(danceSc);
		
		smiPan.setBorder(smi);
		smiPan.add(smi1);
		smiPan.add(smi2);
		smiPan.add(smi3);
		smiPan.add(smi4);
		smiPan.add(smi5);
		smiPan.add(moreSmi);
		add(smiSc);
	}
	
	public void eventInitYoga() {
		videoY1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Desktop d = Desktop.getDesktop();
						d.browse(new URI("https://youtu.be/m_cgE75m5Og"));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});videoY2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/i7IbvLzCmjo"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
		}
	});
		videoY3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Desktop d = Desktop.getDesktop();
						d.browse(new URI("https://youtu.be/BUqB9xoIjLU"));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
		videoY4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Desktop d = Desktop.getDesktop();
						d.browse(new URI("https://youtu.be/yAQcbywKi_M"));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
		videoY5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					try {
						Desktop d = Desktop.getDesktop();
						d.browse(new URI("https://youtu.be/rcp84GZa6C0"));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
			}
		});
	}//end
	
	public void eventInitStretching() {
		videoS1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/1m6SZ4ksbBY"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		videoS11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/w1VCSOAEZJ4"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		videoS2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/FB2OF9Y32zA"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		videoS3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/SAsE6JUbSWQ"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		videoS4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/ZcsUQD5sloc"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		videoS5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/2u97jwzp0Jw"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void eventInitMiley() {
		miley1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/MG69sFM1UIw"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		miley2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/hxjKZcOT17E"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		miley3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/i1ZzdBgLtZg"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		miley4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/hAGfBjvIRFI"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		miley5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/HrpW5PliIdU"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				
			}
		});
		
	}//end

	public void eventInitDance() {
		dance1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/oe6ACKMyF7I"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		dance2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/6SQCPSwf0L4?list=PLB4yVOSJeHiixd5wATDVqJ9cV7JwvVlgs"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		dance3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/nvL_A1XNxVY?list=PLB4yVOSJeHiixd5wATDVqJ9cV7JwvVlgs"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		dance4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/JP508l9jd-w?list=PLB4yVOSJeHiixd5wATDVqJ9cV7JwvVlgs"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		dance5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Desktop d = Desktop.getDesktop();
					d.browse(new URI("https://youtu.be/GcHHIGpWqRM?list=PLB4yVOSJeHiixd5wATDVqJ9cV7JwvVlgs"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
	}
	
	public void eventInitSmi() {

		smi1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dance1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI("https://youtu.be/vFXFZ6Y7AiM"));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
		smi2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dance1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI("https://youtu.be/uYmdt_DW95I"));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
		smi3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dance1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI("https://youtu.be/wfN2cKAv3q0"));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
		smi4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dance1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI("https://youtu.be/bjjJzYn8fEc"));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
		smi5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dance1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI("https://youtu.be/QRGSr9aju_8"));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
		moreSmi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dance1.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							Desktop d = Desktop.getDesktop();
							d.browse(new URI("https://www.youtube.com/results?search_query=%EC%8A%A4%EB%AF%B8%ED%99%88%ED%8A%B8+30%EC%9D%BC+%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8"));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
					}
				});
			}
		});
	}
	
	
	public VideoPan() {
		compInit();
		
		eventInitYoga();
		eventInitMiley();
		eventInitDance();

	}

}
