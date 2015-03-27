<?php
		$email=$_GET["email"];
		$job= $_GET["name"];
		$to      = $email;
		$subject = 'Your InfoCrawler Job is finished';
		$string = 'Your InfoCrawler Job is finished' + $job;
		echo $string;
		$message = 'Your InfoCrawler Job is finished';
		
		// To send HTML mail, the Content-type header must be set
		$headers  = 'MIME-Version: 1.0' . "\r\n";
		$headers .= 'Content-type: text/html; charset=iso-8859-1' . "\r\n";
		// Additional headers
		$headers .= 'From: InfoCrawler<support@ticketvault.cu.cc>' . "\r\n";
		$headers .= 'Cc: '. "\r\n";
		$headers .= 'Bcc: ' . "\r\n";
			// Mail it
		mail($to, $subject, $message, $headers);
?>
