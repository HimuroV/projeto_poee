package com.projeto.view.pedido;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.model.models.Pedido;
import com.projeto.model.models.PrintJasperReport;
import com.projeto.model.service.PedidoService;
import com.projeto.model.service.JasperReportsService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class RelPedido extends JDialog {

	private static final long serialVersionUID = 5063819612401486557L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnGerar;

	public RelPedido(JFrame frame, boolean modal) {
		
		super(frame,modal);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PedidoService pedidoService = new PedidoService();
				PrintJasperReport printJasperReport = new PrintJasperReport();
				JasperReportsService jasperReportsService = new JasperReportsService();
				
				List<Pedido> listaPedido = pedidoService.findAll();
				
				printJasperReport.setFile("rel_pedido");
				printJasperReport.setCollection(listaPedido);
				setVisible(false);
				jasperReportsService.generateListReports(printJasperReport);
			}
		});
		btnGerar.setIcon(new ImageIcon(RelPedido.class.getResource("/com/projeto/estrutura/imagens/pdf.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(163)
					.addComponent(btnGerar)
					.addContainerGap(182, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(108)
					.addComponent(btnGerar)
					.addContainerGap(118, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
