package com.correajose.cineplus.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender emailSender;

    /**
     * Envía un correo electrónico de confirmación
     * @param destinatario dirección de correo del destinatario
     * @param asunto asunto del correo
     * @param contenido contenido del mensaje
     */
    public void enviarCorreoConfirmacion(String destinatario, String asunto, String contenido) {
        SimpleMailMessage mensaje = new SimpleMailMessage();
        mensaje.setTo(destinatario);
        mensaje.setSubject(asunto);
        mensaje.setText(contenido);

        emailSender.send(mensaje);
    }

    /**
     * Ejemplo específico para enviar un correo de confirmación de registro
     * @param destinatario dirección de correo del destinatario
     * @param nombreUsuario nombre del usuario registrado
     * @param tokenConfirmacion token único para confirmar el registro
     */
    public void enviarCorreoConfirmacionRegistro(String destinatario, String nombreUsuario, String tokenConfirmacion) {
        String asunto = "Confirmación de Registro";
        String urlConfirmacion = "https://tuaplicacion.com/confirmar?token=" + tokenConfirmacion;

        String contenido = "Hola " + nombreUsuario + ",\n\n" +
                "Gracias por registrarte. Por favor, confirma tu cuenta haciendo clic en el siguiente enlace:\n\n" +
                urlConfirmacion + "\n\n" +
                "Este enlace expirará en 24 horas.\n\n" +
                "Saludos,\n" +
                "El equipo de tu aplicación";

        enviarCorreoConfirmacion(destinatario, asunto, contenido);
    }
}

