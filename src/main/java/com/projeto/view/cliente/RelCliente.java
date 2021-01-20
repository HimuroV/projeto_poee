package com.projeto.view.cliente;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.projeto.model.models.PrintJasperReport;
import com.projeto.model.models.Cliente;
import com.projeto.model.service.JasperReportsService;
import com.projeto.model.service.ClienteService;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class RelCliente extends JDialog {

	private static final long serialVersionUID = -653090797393408936L;
	private final JPanel contentPanel = new JPanel();
	private JButton btnGerar;


	public RelCliente(JFrame frame, boolean modal) {
		
		super(frame,modal);
		
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		
		btnGerar = new JButton("Gerar");
		btnGerar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ClienteService clienteService = new ClienteService();
				PrintJasperReport printJasperReport = new PrintJasperReport();
				JasperReportsService jasperReportsService = new JasperReportsService();
				
				List<Cliente> listaCliente = clienteService.findAll();
				
				printJasperReport.setFile("rel_cliente");
				printJasperReport.setCollection(listaCliente);
				setVisible(false);
				jasperReportsService.generateListReports(printJasperReport);
			}
		});
		btnGerar.setIcon(new ImageIcon(RelCliente.class.getResource("/com/projeto/estrutura/imagens/pdf.png")));
		GroupLayout gl_contentPanel = new GroupLayout(contentPanel);
		gl_contentPanel.setHorizontalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(165)
					.addComponent(btnGerar)
					.addContainerGap(180, Short.MAX_VALUE))
		);
		gl_contentPanel.setVerticalGroup(
			gl_contentPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPanel.createSequentialGroup()
					.addGap(100)
					.addComponent(btnGerar)
					.addContainerGap(126, Short.MAX_VALUE))
		);
		contentPanel.setLayout(gl_contentPanel);
	}

}
